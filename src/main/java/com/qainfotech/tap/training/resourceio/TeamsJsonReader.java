package com.qainfotech.tap.training.resourceio;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsJsonReader {
	private JSONObject jsonObject;
	List<Individual> individualList;
	List<Individual> inactiveMembers;
	List<Individual> activeMembers;
	List<Team> teamList;
	List<Team> members;
	private Individual newindividual;
	JSONParser parser = new JSONParser();

	/**
	 * get a list of individual objects from db json file
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public List<Individual> getListOfIndividuals() {
		individualList = new ArrayList<>();
		Map<String, Object> input = null;
	       Object reader = null;
			Individual newIndividual1= null;
			JSONObject object = null;
			input = new HashMap<String, Object>();
			try {
				reader = parser.parse(new FileReader("src/main/resources/db.json"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			JSONObject jsonObject = (JSONObject) reader;
			JSONArray individual = (JSONArray) jsonObject.get("individuals");
			for (int inputloop = 0; inputloop < individual.size(); inputloop++) {
				object = (JSONObject) individual.get(inputloop);
				input.put("name", object.get("name"));
				input.put("id", ((Long) object.get("id")).intValue());
				input.put("active", object.get("active"));
				newIndividual1 = new Individual(input);
				 individualList.add(newIndividual1);
			}
			if (individualList == null)
				throw new UnsupportedOperationException("Not implemented.");
			return individualList;
	}

	/**
	 * get individual object by id
	 * 
	 * @param id
	 *            individual id
	 * @return
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public Individual getIndividualById(Integer id) throws ObjectNotFoundException {
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
		throw new ObjectNotFoundException("object not found", "Id", id.toString());
	}

	/**
	 * get individual object by name
	 * 
	 * @param name
	 * @return
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public Individual getIndividualByName(String name) throws ObjectNotFoundException {
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
		throw new ObjectNotFoundException("object not found", "name", name);
	}

	/**
	 * get a list of individual objects who are not active
	 * 
	 * @return List of inactive individuals object
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public List<Individual> getListOfInactiveIndividuals() {
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
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public List<Individual> getListOfActiveIndividuals() {
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
	 * get a list of team objects from db json
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public List<Team> getListOfTeams() {
        Object obj = null;
		Map<String, Object> input1 = new HashMap<String, Object>();
		JSONObject object = null;
		teamList = new ArrayList<>();
		TeamsJsonReader reader = new TeamsJsonReader();
		try {
			obj = parser.parse(new FileReader("src/main/resources/db.json"));
		    jsonObject = (JSONObject) obj;
			JSONArray teams = (JSONArray) jsonObject.get("teams");
			for (int index = 0; index < teams.size(); index++) {
				List<Individual> individualList = new ArrayList<>();
				object = (JSONObject) teams.get(index);
				input1.put("name", object.get("name"));
				input1.put("id", ((Long) object.get("id")).intValue());
				JSONArray memberArray = (JSONArray) object.get("members");
				for (int loc = 0; loc < memberArray.size(); loc++) {
					individualList.add(reader.getIndividualById(((Long) memberArray.get(loc)).intValue()));
				}
				input1.put("members", individualList);
				teamList.add(new Team(input1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return teamList;
    }
	}