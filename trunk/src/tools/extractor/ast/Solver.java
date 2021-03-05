package tools.extractor.ast;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import lib.chrono.TimeMeasure;
import lib.log.Log;

public class Solver {
	public Map<String, EquationAST> unsolved = new HashMap<String, EquationAST>();
	public Map<String, Double> solved = new HashMap<String, Double>();

	/**
	 * Log specific to this class.
	 */
	private static final int LOG = lib.log.Log.getASpecificLogEntry("SOLVER");
	
	public Solver(Map<ArrayList<String>, Vector<String>> benchedUnitBase) throws ParseException {
		super();
		StringBuffer name = new StringBuffer();
		StringBuffer benchedUnit = new StringBuffer();

		Set<ArrayList<String>> keySet = benchedUnitBase.keySet();
		
		Iterator<ArrayList<String>> itKeySet = keySet.iterator();
		EquationAST [] eq = new EquationAST[benchedUnitBase.size()];
		int i = 0;
		while (itKeySet.hasNext()) {
			name = new StringBuffer();
			benchedUnit = new StringBuffer();
			ArrayList<String> splitListName = itKeySet.next();
			Vector<String> benchedUnitListName = benchedUnitBase.get(splitListName);
			int size = splitListName.size();
			for (int j = 0; j < size; j++) {
				name.append(splitListName.get(j));
			}
			size = benchedUnitListName.size();
			for (int j = 0; j < size; j++) {
				benchedUnit.append(benchedUnitListName.get(j));
			}
			Log.log[LOG].println("Solver : name="+name+" BenchedUnit="+benchedUnit);
			byte b [] = benchedUnit.toString().getBytes();
	        ByteArrayInputStream bais = new ByteArrayInputStream(b);
	        eq[i] = new EquationAST(bais);
	        eq[i].Expression();
			unsolved.put(name.toString(), eq[i]);
			i++;
		}
	}
	
	public Map<ArrayList<String>, Vector <TimeMeasure []>> solve(Map<String, TimeMeasure> refAvgBase) throws SolvingException {
		Map<ArrayList<String>, Vector <TimeMeasure []>> ret = new HashMap<ArrayList<String>, Vector <TimeMeasure []>>();
		Map<String, Double> refAvgBase1 = new HashMap<String, Double>();
		Set<String> keySet = refAvgBase.keySet();
		Iterator<String> itKeySet = keySet.iterator();
		while (itKeySet.hasNext()) {
			String nameList = itKeySet.next();
			double val = refAvgBase.get(nameList).getMeasure();
			refAvgBase1.put(nameList, new Double(val));
		}
		
		solve1(refAvgBase1);
		
		Set<String> retKeySet = solved.keySet();
		Iterator<String> retItKeySet = retKeySet.iterator();
		while (retItKeySet.hasNext()) {
			String name = retItKeySet.next();
			ArrayList<String> arraylist = new ArrayList<String>();
			arraylist.add(0,name);
			Vector<TimeMeasure[]> vector = new Vector<TimeMeasure[]>();
			TimeMeasure [] timemeasure = new TimeMeasure[]{
					new TimeMeasure(solved.get(name), TimeMeasure.NANOSECONDS)
			};
			vector.add(timemeasure);
			ret.put(arraylist, vector);
		}
		
		return(ret);
	}
	
	public void solve1(Map<String, Double> refAvgBase) throws SolvingException {
		int sizeSolvedPre = -1, sizeSolvedPost = 0;
		while (sizeSolvedPost!=sizeSolvedPre){ // Looking for a fixed point
			Log.log[LOG].println("solve1 : sizeSolvedPre"+sizeSolvedPre+" ; sizeSolvedPost"+sizeSolvedPost);
			sizeSolvedPre = sizeSolvedPost;
			Vector<String> newlySolved = new Vector<String>(); 
			{
				Set<String> keySet = refAvgBase.keySet();
				Iterator<String> itKeySet = keySet.iterator();
				while (itKeySet.hasNext()) {
					String name = itKeySet.next();
					EquationAST eq = unsolved.get(name);
					if (eq == null) continue; // already solved
					SimpleNode s = (SimpleNode) eq.jjtree.rootNode();
					double val = 0;
					ASTCstNode t = new ASTCstNode(6);
					t.setVal(refAvgBase.get(name).doubleValue());
					t.dump(name + "***");
					try {
						val=s.solve(name, t);
						Log.log[LOG].println(name+"="+val);
					} catch (SolvingException e) {
						Log.log[LOG].println("not solved "+e.getMessage());
						continue; // not solved
					}
					newlySolved.add(name);
					SimpleNode.symbtab.put(name, new Double(val));
					solved.put(name, new Double(val));
					unsolved.remove(name);
				}
			}
			
			
			sizeSolvedPost = solved.size();
			SimpleNode.dumpHash();
		}
		if (unsolved.size() != 0) {
			StringBuffer unsolvedNameList = new StringBuffer();
			Set<String> keySet = refAvgBase.keySet();
			Iterator<String> itKeySet = keySet.iterator();
			while (itKeySet.hasNext()) {
				String name = itKeySet.next();
				unsolvedNameList.append(name+"; ");
			}
			throw new SolvingException("System unsolved : " + unsolvedNameList.toString());
		}

	}
	
}
