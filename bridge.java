import java.util.*;

public class bridge extends State{

    public static void main(String[] args) {

        ArrayList<Integer> time = new ArrayList<Integer>();
        ArrayList<Integer> t = new ArrayList<Integer>();

        ArrayList<Integer> x;

        ArrayList<Integer> copied = new ArrayList<Integer>();
        ArrayList<State> possible = new ArrayList<State>();
        ArrayList<State> solution = new ArrayList<State>();
        ArrayList<Integer> sol;

        try{
            Scanner obj = new Scanner(System.in);       
            System.out.print("Enter number of members: ");
            int members = obj.nextInt();
            while(members< 3){
                System.out.println("There must be at list 3 members.");
                obj = new Scanner(System.in);       
                System.out.print("Enter number of members: ");
                members = obj.nextInt();
            }

            for (int i = 1; i <= members; i++) {
                obj = new Scanner(System.in);
                System.out.print("Enter the speed of the " + i + "st member: ");
                time.add(obj.nextInt());
            }

            obj = new Scanner(System.in);
            System.out.print("Give max total time: ");
            int maxCost = obj.nextInt();

            State root = new State();
            
            root.setH(Collections.max(time));
            root.setF(root.getH());
            root.setTotalTime(0);
            root.setRight(time);
            root.setGo(true);

            State s;
            int min,p;
            State temp = root;
            boolean end = false;
            int cost = 0;

            while(!end && cost <= maxCost){

                if(temp.getGo()){

                    time = temp.getRight();

                    if(time.size()==2){
                        s = new State(temp);
                        
                        s.setLeft(time.get(0));
                        s.setLeft(time.get(1)); 
                    
                        for(int i = 0; i < s.getFather().getLeft().size();i++){
                            s.setLeft(s.getFather().getLeft().get(i));

                        } 

                        s.setG(s.getFather().getG() + Math.max(time.get(0),time.get(1)));
                        s.setTotalTime(s.getG());

                        s.evaluate();
                        temp.setH(0);
                        temp = s;  
                        
                    }else{

                        for(int i = 0; i<time.size(); i++){
                            for(int j = 1+i; j<time.size(); j++){
                                s = new State(temp);

                                if(!temp.getLeft().isEmpty()){
                                    for(int h = 0; h < s.getFather().getLeft().size(); h++){
                                        s.setLeft(s.getFather().getLeft().get(h));

                                    } 
                                }

                                s.setLeft(time.get(i));
                                s.setLeft(time.get(j));
                                copied = new ArrayList<>(time);
                                copied.remove(Integer.valueOf(time.get(i)));
                                copied.remove(Integer.valueOf(time.get(j)));
                                s.setRight(copied);

                                s.setG(s.getFather().getG() + Math.max(time.get(i),time.get(j)));
                                s.setTotalTime(s.getG());
                                Collections.sort(copied);
                                s.setH(copied.get(copied.size()-1));
                                s.evaluate();
                                temp.setChildren(s);
                                s.setGo(false);
                                possible.add(s);

                            }
                            
                        }
                    }
                }else{
                    for(int i = 0; i<temp.getLeft().size(); i++){
                        s = new State(temp);

                        x = new ArrayList<>(s.getFather().getLeft());
                        x.remove(Integer.valueOf(s.getFather().getLeft().get(i)));
                        s.setLeft(x);

                        x = new ArrayList<>(s.getFather().getRight());
                        x.add(s.getFather().getLeft().get(i));
                        s.setRight(x);

                        s.setG(s.getFather().getG() + s.getFather().getLeft().get(i));
                        s.setTotalTime(s.getG());

                        Collections.sort(x);
                        s.setH(x.get(x.size()-1));
                        s.evaluate();
                        temp.setChildren(s);
                        
                        s.setGo(true);

                        possible.add(s);
                        
                    }
                }
                

                if(temp.getH()!=0){

                    p=0;
                    min  = possible.get(0).getF();
                    temp = possible.get(0);
                    for(int i =0; i<possible.size(); i++){
                        if(min > possible.get(i).getF()){
                            min = possible.get(i).getF(); //where to expand
                            temp = possible.get(i);
                            p=i;
                        }
                    } 
                    possible.remove(p);

                    cost = temp.getTotalTime();
                }else{

                    cost = temp.getTotalTime();

                    if(cost <= maxCost){

                        while(temp.getFather()!=null){
                            solution.add(temp);
                            temp = temp.getFather();

                        }
                        solution.add(temp);

                        System.out.println("\n---------------SOLUTION---------------\n");

                        System.out.println(solution.get(solution.size()-1).getLeft()+ " ----- "+solution.get(solution.size()-1).getRight());


                        for(int i = solution.size()-1; i > 0; i--){

                            if(solution.get(i).getRight().size() > solution.get(i-1).getRight().size()){

                                sol = new ArrayList<>(solution.get(i).getRight());
                                sol.removeAll(solution.get(i-1).getRight());
                                
                                if(i % 2 != 0){
                                    System.out.println("This numbers passing the bridge: "+sol);
                                    System.out.println(solution.get(i-1).getLeft()+ " ----- "+solution.get(i-1).getRight());

                                    //cost += Math.max(sol.get(0),sol.get(1));
                                }else{
                                    System.out.println("This number return: "+ sol);
                                    System.out.println(solution.get(i-1).getLeft()+ " ----- "+solution.get(i-1).getRight());

                                    //cost+= sol.get(0);
                                }
                            }else{

                                sol = new ArrayList<>(solution.get(i-1).getRight());
                                sol.removeAll(solution.get(i).getRight());

                                if(i % 2 != 0){
                                    System.out.println("This numbers "  + sol +" passing the bridge. ");
                                    System.out.println(solution.get(i-1).getLeft()+ " ----- "+solution.get(i-1).getRight());
                 
                                    //cost += Math.max(sol.get(0),sol.get(1));

                                }else{
                                    System.out.println("This number return: "+ sol);                                    
                                    System.out.println(solution.get(i-1).getLeft()+ " ----- "+solution.get(i-1).getRight());

                                    //cost+= sol.get(0);

                                } 
                            }
                        }
                    }
                    
                    end = true;
                    
                }
                
            
            }
            if(cost <= maxCost){
                System.out.println("The total time is: "+cost);

            }else{
                System.out.println("\nThere is no solution for tis time!");
            }
        }catch (Exception e) {
            System.out.println("Wrong Input!");
            
        }
    
    }

}


