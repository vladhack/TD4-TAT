import static org.junit.Assert.*;

import org.jmlspecs.utils.JmlAssertionError;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestExplosivesFindBat {

    static int nb_inconclusive = 0;
    static int nb_fail = 0;

    Explosives e;

    public static void main(String args[]) {
    	String testClass = "TestExplosivesFindBat";
     	org.junit.runner.JUnitCore.main(testClass);
     }


    private void handleJMLAssertionError(JmlAssertionError e) {
    	if (e.getClass().equals(JmlAssertionError.PreconditionEntry.class) || e.getClass().equals(JmlAssertionError.Precondition.class)) {
    	    System.out.println("\n INCONCLUSIVE "+(new Exception().getStackTrace()[1].getMethodName())+ "\n\t "+ e.getMessage());
            nb_inconclusive++;}
    else{
	    // test failure	
	    nb_fail++;
	    fail("\n\t" + e.getMessage());	
		}  
    }
	
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		nb_inconclusive = 0;
		nb_fail = 0;
		org.jmlspecs.utils.Utils.useExceptions = true;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	     System.out.println("\n inconclusive tests: "+nb_inconclusive+" -- failures : "+nb_fail );
	}
	
	@Test
	public void  testSequence_1() {
		try{
			e=new Explosives();
			e.add_incomp("Prod_Mite","Prod_Nitro");
			e.add_assign("Bat_1", "Prod_Mite");
			e.add_assign("Bat_1", "Prod_Dynamite");
			System.out.println(e.findBat("Prod_Nitro"));
			System.out.println(e.findBat("Prod_Ex"));
		} 	catch(JmlAssertionError e){
				handleJMLAssertionError(e);		
		}  
	}
}
