public class PrimeChecker{
    

    public static boolean isPrime(int numberToCheck){
        int divisor = 2;
        
        for (;(numberToCheck > divisor) && (numberToCheck-1 >= divisor); divisor++){
            if (numberToCheck % divisor == 0){
                return false;
            }
             
            
        }
        return true;
    }

    public static void main(String[] args) {
       for (int numberToCheck = 1; numberToCheck <=25;numberToCheck++){
        if(isPrime(numberToCheck)){
            System.out.println( numberToCheck + " is a prime number ");
        }
        else{
            System.out.println( numberToCheck + " is not a prime number ");
        }
       }
        

        
    }

}
