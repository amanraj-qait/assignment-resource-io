package com.qainfotech.tap.training.resourceio;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;


/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsYamlReader{
	List<Individual> individualList;
	List<Individual> inactiveMembers;
	List<Individual> activeMembers;
	List<Team> teamList;
	List<Team> members;
	String path= "db.yaml";
	private Individual newindividual;
	ClassLoader Loader = this.getClass().getClassLoader();
    /**
     * get a list of individual objects from db yaml file
     * 
     * @return 
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Individual> getListOfIndividuals(){
       // throw new UnsupportedOperationException("Not implemented.");
    	individualList = new ArrayList<>();
    	Yaml readYaml = new Yaml();
    	Map<String, Object> inputMap = new HashMap<String, Object>();
    	InputStream input = Loader.getResourceAsStream(path);
		Map<String, Object> result=(Map<String, Object>) readYaml.load(input);
		ArrayList inputIndividual= (ArrayList) result.get("individuals");	
		for(int  index=0; index<inputIndividual.size(); index++) {
			inputMap =(Map<String, Object>)inputIndividual.get( index);
			Individual newindividual1=new Individual(inputMap);
				individualList.add(newindividual1);	
				}
		return individualList;
    	
    }
    
    /**
     * get individual object by id
     * 
     * @param id individual id
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     */
    public Individual getIndividualById(Integer id) throws ObjectNotFoundException{
    	System.out.println(" Id value" + id);
		individualList = getListOfIndividuals();
		Iterator<Individual> iterate = individualList.iterator();
		newindividual = null;
		while (iterate.hasNext()) {
			newindividual = iterate.next();
			if (newindividual.getId() == id.intValue()) {
				return newindividual;
			} else {
				System.out.println(newindividual);
			}
			if (newindividual == null)
				throw new UnsupportedOperationException("Not implemented.");
		}
		throw new ObjectNotFoundException("Individual", "id", id.toString());
    }
    
    /**
     * get individual object by name
     * 
     * @param name
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     */
    public Individual getIndividualByName(String name) throws ObjectNotFoundException{
    	individualList = getListOfIndividuals();
		Iterator<Individual> iterate = individualList.iterator();
		newindividual = null;
		while (iterate.hasNext()) {
			newindividual = iterate.next();
			if (newindividual.getName().equalsIgnoreCase(name)) {
				return newindividual;
			}
			if (newindividual == null)
				throw new UnsupportedOperationException("Not implemented.");
		}
		throw new ObjectNotFoundException("Individual", "Name", name);
	}

    
    
    /**
     * get a list of individual objects who are not active
     * 
     * @return List of inactive individuals object
     */
    public List<Individual> getListOfInactiveIndividuals(){
    	individualList = getListOfIndividuals();
		Iterator<Individual> iterate = individualList.iterator();
		inactiveMembers = new ArrayList<>();
		newindividual = null;
		while (iterate.hasNext()) {
			newindividual = iterate.next();
			if (!(newindividual.isActive()))
				inactiveMembers.add(newindividual);
		}
		if (inactiveMembers == null)
			throw new UnsupportedOperationException("Not implemented.");
		return inactiveMembers;
	}
    
    /**
     * get a list of individual objects who are active
     * 
     * @return List of active individuals object
     */
    public List<Individual> getListOfActiveIndividuals(){
    	individualList = getListOfIndividuals();
		Iterator<Individual> iterate = individualList.iterator();
		activeMembers = new ArrayList<>();
		newindividual = null;
		while (iterate.hasNext()) {
			newindividual = iterate.next();
			if (newindividual.isActive())
				activeMembers.add(newindividual);
		}
		if (activeMembers == null)
			throw new UnsupportedOperationException("Not implemented.");
		return activeMembers;
	}
    /**
     * get a list of team objects from db yaml
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Team> getListOfTeams(){
  teamList = new ArrayList<>();
    	 	Map<String, Object> inputMap2 = new HashMap<String, Object>();
    	 		TeamsYamlReader reader = new TeamsYamlReader();
    	 		try {
    	 			InputStream input = Loader.getResourceAsStream(path);
    	 			Yaml readYaml1 = new Yaml();
    	 			Map<String, Object> result1 = (Map<String, Object>) readYaml1.load(input);
    	 			ArrayList inputTeam= (ArrayList) result1.get("teams");
    	 			Map<String, Object> object;
    	 
    	 			for (int inputloop = 0; inputloop < inputTeam.size(); inputloop++) {
    	 				List<Individual> individualList1 = new ArrayList<>();
    	 				object = (Map<String, Object>) inputTeam.get(inputloop);
    	 				inputMap2.put("name", object.get("name"));
    	 				inputMap2.put("id", object.get("id"));
    	 				List listofmembers = (List) object.get("members");
    	 				for (int loc = 0; loc < listofmembers.size(); loc++) {
    	 					individualList1.add(reader.getIndividualById((Integer) listofmembers.get(loc)));
    	 
    	 				}
    	 				inputMap2.put("members", individualList1);
    	 				teamList.add(new Team(inputMap2));
    	 			}
    	 
    	 		} catch (ObjectNotFoundException e) {
    	 			e.printStackTrace();
    	 		}
    	 		return teamList;
    }
}