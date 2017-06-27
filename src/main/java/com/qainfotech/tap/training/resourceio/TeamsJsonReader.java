package com.qainfotech.tap.training.resourceio;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

import java.io.File;
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

	/**
	 * get a list of individual objects from db json file
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public List<Individual> getListOfIndividuals() {
		JSONParser parser = new JSONParser();
		FileReader readfile = null;
		try {
			readfile = new FileReader(new File(
					"C:\\Users\\amanraj\\workspace\\assignment-resource-io-master\\src\\test\\resources\\db.json"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			jsonObject = (JSONObject) parser.parse(readfile);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		individualList = new ArrayList<>();
		JSONArray individual = (JSONArray) jsonObject.get("individuals");
		Map<String, Object> input = new HashMap<String, Object>();
		for (int inputloop = 0; inputloop < individual.size(); inputloop++) {
			JSONObject ob = (JSONObject) individual.get(inputloop);
			input.put("1", ob);
			Individual newindividual = new Individual(input);
			if (newindividual != null)
				individualList.add(newindividual);
			input.remove("1");
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
		JSONParser parser = new JSONParser();
		FileReader readfile = null;
		try {
			readfile = new FileReader(new File(
					"C:\\Users\\amanraj\\workspace\\assignment-resource-io-master\\src\\test\\resources\\db.json"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			jsonObject = (JSONObject) parser.parse(readfile);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		teamList = new ArrayList<>();
		JSONArray teams = (JSONArray) jsonObject.get("teams");
		Map<String, Object> input = new HashMap<String, Object>();
		for (int i = 0; i < teams.size(); i++) {
			JSONObject ob = (JSONObject) teams.get(i);
			input.put("1", ob);
			Team newindividual = null;
			newindividual = new Team(input);
			if (newindividual != null)
				teamList.add(newindividual);
			input.remove("1");

		}
		if (teamList == null)
			throw new UnsupportedOperationException("Not implemented.");
		return teamList;
	}

}
