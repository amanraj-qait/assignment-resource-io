package com.qainfotech.tap.training.resourceio.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class Team {

	private final String name;
	private final Integer id;
	private final List<Individual> members;

	/**
	 * Initialized the global variables name, id and members
	 * 
	 * @param teamMap
	 */
	@SuppressWarnings("unchecked")
	public Team(Map<String, Object> teamMap) {
		this.name=(String) teamMap.get("name");
	       this.id=(Integer) teamMap.get("id");
	       this.members=  (List<Individual>) teamMap.get("members");
		if (members == null)
			throw new UnsupportedOperationException("Not implemented.");
	}

	/**
	 * get team name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * get team id
	 * 
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * get list of individuals that are members of this team
	 * 
	 * @return
	 */
	public List<Individual> getMembers() {
		return members;
	}

	/**
	 * get a list of individuals that are members of this team and are also
	 * active
	 * 
	 * @return
	 */
	public List<Individual> getActiveMembers() {
		// throw new UnsupportedOperationException("Not implemented.");
		List<Individual> activeMembersOfTeam = new ArrayList<>();
		Iterator<Individual> iterate = this.members.iterator();
		while (iterate.hasNext()) {
			Individual individual = iterate.next();
			if (individual.isActive()) {
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
	public List<Individual> getInactiveMembers() {
		// throw new UnsupportedOperationException("Not implemented.");
		List<Individual> inactiveMembersListOfTeam = new ArrayList<>();
		Iterator<Individual> iterate = this.members.iterator();
		while (iterate.hasNext()) {
			Individual individual = iterate.next();
			if (!(individual.isActive())) {
				inactiveMembersListOfTeam.add(individual);
			}
		}
		return inactiveMembersListOfTeam;
	}
}
