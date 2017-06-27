package com.qainfotech.tap.training.resourceio.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.qainfotech.tap.training.resourceio.TeamsJsonReader;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class Team {
    
    private final String name;
    private final Integer id;
    private final List<Individual> members;

    
    public Team(Map<String, Object> teamMap){
    	Object[] values = teamMap.values().toArray();
    	JSONObject jsonObject=new JSONObject();
    	 jsonObject=(JSONObject) values[0];
    	this.name=jsonObject.get("name").toString();
    	this.id=Integer.parseInt(jsonObject.get("id").toString());
    	this.members=new ArrayList<>();
    	List<Individual> arrayOfIndividuals = null;
			arrayOfIndividuals = (new TeamsJsonReader()).getListOfIndividuals();
    	JSONArray memberArray=(JSONArray) jsonObject.get("members");
    	Iterator<Individual> iterate=arrayOfIndividuals.iterator();
    	while(iterate.hasNext()){
    		Individual individual=iterate.next();
    		for(int i=0;i<memberArray.size();i++){
    			if(individual.getId()==Integer.parseInt(memberArray.get(i).toString())){
    				members.add(individual);
    			}
    			}
    		if(individual==null)
    			throw new UnsupportedOperationException("Not implemented.");
    			}
if(members ==null )
	  throw new UnsupportedOperationException("Not implemented.");
    }
    
    /**
     * get team name
     * 
     * @return 
     */
    public String getName(){
        return name;
    }
    
    /**
     * get team id
     * 
     * @return 
     */
    public Integer getId(){
        return id;
    }
    
    /** 
     * get list of individuals that are members of this team
     * 
     * @return 
     */
    public List<Individual> getMembers(){
        return members;
    }
    
    /**
     * get a list of individuals that are members of this team and are also active
     * 
     * @return 
     */
    public List<Individual> getActiveMembers(){
    	//  throw new UnsupportedOperationException("Not implemented.");
    	List<Individual> activeMembersOfTeam=new ArrayList<>();
    	Iterator<Individual> iterate=this.members.iterator();
    	while(iterate.hasNext()){
    		Individual individual=iterate.next();
    		if(individual.isActive()){
    			activeMembersOfTeam.add(individual);
    		}
    	}
    	return activeMembersOfTeam;
    }
        
    /**
     * get a list of individuals that are members of this team but are inactive
     * 
     * @return 
     */
    public List<Individual> getInactiveMembers(){
    	//throw new UnsupportedOperationException("Not implemented.");
    	List<Individual> inactiveMembersListOfTeam=new ArrayList<>();
    	Iterator<Individual>iterate=this.members.iterator();
    	while(iterate.hasNext()){
    		Individual individual=iterate.next();
    		if(!(individual.isActive())){
    			inactiveMembersListOfTeam.add(individual);
    		}
    	}
    	return inactiveMembersListOfTeam;
    }
}
