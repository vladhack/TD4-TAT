// Based on a B specification from Marie-Laure Potet.

public class Explosives{
    public int nb_inc = 0;
    public String [][] incomp = new String[50][2];
    public int nb_assign = 0;
    public String [][] assign = new String[30][2];
 
    /*@ public invariant // Prop 1
      @ (0 <= nb_inc && nb_inc < 50);
      @*/
    /*@ public invariant // Prop 2
      @ (0 <= nb_assign && nb_assign < 30);
      @*/
    /*@ public invariant // Prop 3
      @ (\forall int i; 0 <= i && i < nb_inc; 
      @         incomp[i][0].startsWith("Prod") && incomp[i][1].startsWith("Prod"));
      @*/
    /*@ public invariant // Prop 4
      @ (\forall int i; 0 <= i && i < nb_assign; 
      @         assign[i][0].startsWith("Bat") && assign[i][1].startsWith("Prod"));
      @*/
    /*@ public invariant // Prop 5
      @ (\forall int i; 0 <= i && i < nb_inc; !(incomp[i][0]).equals(incomp[i][1]));
      @*/
    /*@ public invariant // Prop 6
      @ (\forall int i; 0 <= i && i < nb_inc; 
      @        (\exists int j; 0 <= j && j < nb_inc; 
      @           (incomp[i][0]).equals(incomp[j][1]) 
      @              && (incomp[j][0]).equals(incomp[i][1]))); 
      @*/
    /*@ public invariant // Prop 7
      @ (\forall int i; 0 <= i &&  i < nb_assign; 
      @     (\forall int j; 0 <= j && j < nb_assign; 
      @        (i != j && (assign[i][0]).equals(assign [j][0])) ==>
      @        (\forall int k; 0 <= k && k < nb_inc;
      @           (!(assign[i][1]).equals(incomp[k][0])) 
      @              || (!(assign[j][1]).equals(incomp[k][1])))));
      @*/



    //@ requires 0 <= nb_inc && nb_inc < 48;
    //@ requires !prod1.equals(prod2);
    //@ requires prod1.startsWith("Prod") && prod2.startsWith("Prod");
    public void add_incomp(String prod1, String prod2){
	incomp[nb_inc][0] = prod1;
	incomp[nb_inc][1] = prod2;
	incomp[nb_inc+1][1] = prod1;
	incomp[nb_inc+1][0] = prod2;
	nb_inc = nb_inc+2;
     }


    //@ requires 0 <= nb_assign && nb_assign < 29;
    //@ requires prod.startsWith("Prod") && bat.startsWith("Bat");
    //@ requires (\forall int d; 0<= d && d< nb_assign; ( (assign[d][0]).equals(bat) ==> !(\exists int f; 0<= f && f < nb_inc; (incomp[f][0]).equals(assign[d][1]) && (incomp[f][1]).equals(prod) ) ) );
    public void add_assign(String bat, String prod){
	assign[nb_assign][0] = bat;
	assign[nb_assign][1] = prod;
	nb_assign = nb_assign+1;
    }
    
    public void skip(){
    }

    //@ requires prod1.startsWith("Prod") && prod2.startsWith("Prod");
	//@ ensures ( (\result == true) ==> (\forall int d; 0 <= d && d < nb_inc; !(incomp[d][0].equals(prod1) && incomp[d][1].equals(prod2)) ) );
	//@ ensures ( (\result == false) ==> (\exists int d; 0 <= d && d < nb_inc; incomp[d][0].equals(prod1) && incomp[d][1].equals(prod2) ) );
	public /*@ pure @*/ boolean compatible(String prod1, String prod2){
    	for(int i = 0; i < nb_inc; i++)
    		if(incomp[i][0].equals(prod1) && incomp[i][1].equals(prod2)) return false;
    	return true;
    }
    
    //@ ensures (\forall int f; 0<= f && f< nb_assign; !(\result.equals(assign[f][0])));
    public /*@ pure @*/ String  newBat(){
    	int cmpt = 0;
    	boolean alreadyExists = true;
    	while(alreadyExists){
	    	cmpt++;
	    	alreadyExists = false;
	    	for(int i = 0; i < nb_assign; i++)
	    		if(assign[i][0].equals("Bat_"+cmpt)) alreadyExists = true;
    	}
    	return "Bat_"+cmpt;
    }
    
    //@ requires Prod.startsWith("Prod");
    //@ ensures \result.startsWith("Bat");
    //@ ensures (nb_assign > 0) ==> ( (\result.equals(newBat())) <==> !(\exists int d; 0<= d && d< nb_assign; (\forall int f; 0<= f && f< nb_assign; (assign[d][0].equals(assign[f][0])) ==> compatible(assign[f][1],Prod)) ) ) || ( !(\result.equals(newBat())) <==> (\forall int f; 0<= f && f< nb_assign; (\forall int d; 0<= d && d< nb_assign; (assign[f][0].equals(assign[d][0])) ==> (compatible(assign[d][1],Prod)) ) ) );
    //@ ensures (nb_assign == 0) ==> ( \result.equals(newBat()) );
    public /*@ pure @*/ String findBat(String Prod){
    	for(int i = 0; i < nb_assign; i++){
    		boolean compatible = true;
    		for(int j = 0; j < nb_assign; j++){
    			if(assign[i][0].equals(assign[j][0]) && !compatible(Prod,assign[j][1]))
    				compatible = false;
    		}
    		if(compatible) return assign[i][0];
    	}
    	return newBat();
    }
}
