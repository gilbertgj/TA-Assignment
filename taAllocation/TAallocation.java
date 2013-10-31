package taAllocation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class TAallocation extends PredicateReader implements TAallocationPredicates {
	
	public TAallocation(String name)  {
		super(name);
	}
	
	public static void main(String[] args) {
		if (args.length == 0) {
			commandMode(new TAallocation(""));
		}
		else if (args.length != 2) {
			System.out.println("\nError: Enter [filename] [maxtime] as args");
		}
		else {
			String filename = args[0];
			int maxtime = Integer.parseInt(args[1]);
			/*
			PredicateReader env = new PredicateReader("");
			if (env.fromFile(filename) != -1) {
				createOutputFile(filename + ".out");
			}
			*/
			createOutputFile(filename + ".out");
		}
	}
	
	private static void createOutputFile(String filename) {
		try {
			PrintStream out = new PrintStream(new FileOutputStream(filename));
		} catch (FileNotFoundException e) {
			error("Can't create file " + filename);
		}
	}

	/**
	 * Implement "command mode": repeatedly read lines of predicates
	 * from {@link System#in} and either assert them (if the line starts
	 * with a "!") or evaluate them (and return "true" or "false" to
	 * {@link System#out}. 
	 * @param env the environment that can interpret the predicates.
	 */
	public static void commandMode(PredicateReader env) { // static?
		final int maxBuf = 200;
		byte[] buf = new byte[maxBuf];
		int length;
		try {
			System.out.print("\nSisyphus I: query using predicates, assert using \"!\" prefixing predicates;\n !exit() to quit; !help() for help.\n\n> ");
			while ((length=System.in.read(buf))!=-1) {
				String s = new String(buf,0,length);
				s = s.trim();
				if (s.equals("exit")||s.equals("!exit()")) break;
				if (s.equals("?")||s.equals("help")) {
					s = "!help()";
					System.out.println("> !help()");
				}
				if (s.length()>0) {
					if (s.charAt(0)=='!') {
						env.assert_(s.substring(1));
					}
					else { 
						System.out.print(" --> "+env.eval(s));
					}
				}
				System.out.print("\n> ");
			}
		} catch (Exception e) {
			System.out.println("exiting: "+e.toString());
		}
	}
	// <copy commandMode(PredicateReader env) out of Test.java>
	// public static void main(String[] p) {
	//     commandMode(this);
	// }
	
	public void print(String s) {
		System.out.print(s);
	}
	
	public void println(String s) {
		System.out.println(s);
	}

	@Override
	public void a_maxlabs(Long p) {
		
	}

	@Override
	public void a_minlabs(Long p) {
		
	}

	@Override
	public void a_TA(String p) {
		
	}

	@Override
	public boolean e_TA(String p) {
		return false;
	}

	@Override
	public void a_instructor(String p) {
		
	}

	@Override
	public boolean e_instructor(String p) {
		return false;
	}

	@Override
	public void a_course(String p) {
		
	}

	@Override
	public boolean e_course(String p) {
		return false;
	}

	@Override
	public void a_senior_course(String p) {
		
	}

	@Override
	public boolean e_senior_course(String p) {
		return false;
	}

	@Override
	public void a_grad_course(String p) {
		
	}

	@Override
	public boolean e_grad_course(String p) {
		return false;
	}

	@Override
	public void a_timeslot(String p) {
		
	}

	@Override
	public boolean e_timeslot(String p) {
		return false;
	}

	@Override
	public void a_lecture(String c, String lec) {
		
	}

	@Override
	public boolean e_lecture(String c, String lec) {
		return false;
	}

	@Override
	public void a_lab(String c, String lec, String lab) {
		
	}

	@Override
	public boolean e_lab(String c, String lec, String lab) {
		return false;
	}

	@Override
	public void a_instructs(String p, String c, String l) {
		
	}

	@Override
	public boolean e_instructs(String p, String c, String l) {
		return false;
	}

	@Override
	public void a_at(String c, String l, String t) {
		
	}

	@Override
	public boolean e_at(String c, String l, String t) {
		return false;
	}

	@Override
	public void a_knows(String ta, String c) {
		
	}

	@Override
	public boolean e_knows(String ta, String c) {
		return false;
	}

	@Override
	public void a_prefers(String instructor, String ta, String c) {
		
	}

	@Override
	public boolean e_prefers(String instructor, String ta, String c) {
		return false;
	}

	@Override
	public void a_prefers1(String ta, String c) {
		
	}

	@Override
	public boolean e_prefers1(String ta, String c) {
		return false;
	}

	@Override
	public void a_prefers2(String ta, String c) {
		
	}

	@Override
	public boolean e_prefers2(String ta, String c) {
		return false;
	}

	@Override
	public void a_prefers3(String ta, String c) {
		
	}

	@Override
	public boolean e_prefers3(String ta, String c) {
		return false;
	}

	@Override
	public void a_taking(String ta, String c, String l) {
		
	}

	@Override
	public boolean e_taking(String ta, String c, String l) {
		return false;
	}

	@Override
	public void a_conflicts(String t1, String t2) {
		
	}

	@Override
	public boolean e_conflicts(String t1, String t2) {
		return false;
	}
}
