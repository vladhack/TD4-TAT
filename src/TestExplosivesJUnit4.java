import static org.junit.Assert.*;

import org.jmlspecs.utils.JmlAssertionError;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestExplosivesJUnit4 {

    static int nb_inconclusive = 0;
    static int nb_fail = 0;

    Explosives e;

    public static void main(String args[]) {
    	String testClass = "TestExplosivesJUnit4";
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
	public void  testSequence_0() {
		try{
			e=new Explosives();
			e.add_incomp("Prod_Nitro","Prod_Glycerine");
			e.add_incomp("Prod_Dyna","Prod_Mite");
			e.add_assign("Bat_1","Prod_Dyna");
			e.add_assign("Bat_1","Prod_Nitro");
			e.add_assign("Bat_2","Prod_Mite");
			e.add_assign("Bat_1","Prod_Glycerine");
		} 	catch(JmlAssertionError e){
				handleJMLAssertionError(e);		
		}  
	}
	
	//Test invalidant l'invariant Prop1
	@Test
	public void  testSequence_1() {
		try{
			e=new Explosives();
			for(int i=0; i<=50; i++){
				e.add_incomp("Prod_Mite","Prod_Nitro");
			}
		} 	catch(JmlAssertionError e){
				handleJMLAssertionError(e);		
		}  
	}

	//Test invalidant l'invariant Prop2
	@Test
	public void  testSequence_2() {
		try{
			e=new Explosives();
			for(int i=0; i<=30; i++){
				e.add_assign("Bat_1","Prod_Nitro");
			}
		} 	catch(JmlAssertionError e){
				handleJMLAssertionError(e);		
		}  
	}

	//Test invalidant l'invariant Prop3
	@Test
	public void  testSequence_3() {
		try{
			e=new Explosives();
			e.add_incomp("Bat_1","Prod_Nitro");
		} 	catch(JmlAssertionError e){
				handleJMLAssertionError(e);		
		}  
	}

	//Test invalidant l'invariant Prop4
	@Test
	public void  testSequence_4() {
		try{
			e=new Explosives();
			e.add_assign("Prod_Mite","Prod_Nitro");
			
		} 	catch(JmlAssertionError e){
				handleJMLAssertionError(e);		
		}  
	}

	//Test invalidant l'invariant Prop5
	@Test
	public void  testSequence_5() {
		try{
			e=new Explosives();
			e.add_incomp("Prod_Nitro","Prod_Nitro");
			
		} 	catch(JmlAssertionError e){
				handleJMLAssertionError(e);		
		}  
	}

	//Test invalidant l'invariant Prop6
	/*
	 * Ce test ne peut se faire en n'utilisant que les fonctions de la classe Explosives fournies.
	 * Il se trouve dans la classe de test TestExplosivesJUnit4Public
	 */

	//Test invalidant l'invariant Prop7
	@Test
	public void  testSequence_7() {
		try{
			e=new Explosives();
			e.add_incomp("Prod_Mite","Prod_Nitro");
			e.add_assign("Bat_1", "Prod_Mite");
			e.add_assign("Bat_1", "Prod_Nitro");
		} 	catch(JmlAssertionError e){
				handleJMLAssertionError(e);		
		}  
	}


}
