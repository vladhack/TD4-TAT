import static org.junit.Assert.fail;

import org.jmlspecs.utils.JmlAssertionError;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestExplosivesJUnit4Public {

    static int nb_inconclusive = 0;
    static int nb_fail = 0;

    Explosives e;

    public static void main(String args[]) {
    	String testClass = "TestExplosivesJUnit4Public";
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
	
	//Test invalidant l'invariant Prop6
	@Test
	public void  testSequence_6() {
		try{
			e=new Explosives();
			e.add_incomp("Prod_Mite","Prod_Nitro");
			e.incomp[e.nb_inc-1][0]= null;
			e.incomp[e.nb_inc-1][1]= null;
			e.nb_inc--;
			e.skip();
		} 	catch(JmlAssertionError e){
				handleJMLAssertionError(e);		
		}  
	}
}
