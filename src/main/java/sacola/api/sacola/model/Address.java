package sacola.api.sacola.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

import java.util.regex.Matcher;

public class Address {

    private static final String CEP_REGEX = "^[0-9]{5}-?[0-9]{3}$";

    private String cep;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String numero;
    private String complemento;

    public Address(String cep, String rua, String bairro, String cidade, String estado, String numero,
            String complemento) {
        if (!isValidCep(cep) || !isCepValidWithApi(cep)) {
            throw new IllegalArgumentException("CEP inválido");
        }
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
        this.complemento = complemento;
    }

    public Address() {
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        if (!isValidCep(cep) || !isCepValidWithApi(cep)) {
            throw new IllegalArgumentException("CEP inválido");
        }
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    private boolean isValidCep(String cep) {
        if (cep == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(CEP_REGEX);
        Matcher matcher = pattern.matcher(cep);
        return matcher.matches();
    }

    private boolean isCepValidWithApi(String cep) {
        try {
            String url = "https://viacep.com.br/ws/" + cep.replace("-", "") + "/json/";

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                String jsonResponse = content.toString();
                return !jsonResponse.contains("\"erro\": true");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isValidAddress(String endereco) {
        return endereco != null && !endereco.trim().isEmpty();
    }

}
