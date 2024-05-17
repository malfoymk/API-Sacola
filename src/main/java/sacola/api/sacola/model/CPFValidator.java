package sacola.api.sacola.model;



public class CPFValidator {

    @SuppressWarnings("unused")
    private static final String CPF_REGEX = "^[0-9]{11}$";

    public static boolean isValidCPF(String cpf) {
        
        cpf = cpf.replaceAll("[^0-9]", "");

        
        if (cpf.length() != 11) {
            return false;
        }

       
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

       
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int firstDigitVerifier = 11 - (sum % 11);
        firstDigitVerifier = (firstDigitVerifier >= 10) ? 0 : firstDigitVerifier;

        
        if (firstDigitVerifier != Character.getNumericValue(cpf.charAt(9))) {
            return false;
        }

        
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int secondDigitVerifier = 11 - (sum % 11);
        secondDigitVerifier = (secondDigitVerifier >= 10) ? 0 : secondDigitVerifier;

        
        return secondDigitVerifier == Character.getNumericValue(cpf.charAt(10));
    }
}
