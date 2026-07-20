package com.danielazevedo.blogtech.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EnderecoRequestDTO {

    @NotBlank(message = "Informe o CEP.")
    @Pattern(regexp = "\\d{5}-?\\d{3}", message = "Informe um CEP válido.")
    private String cep;

    @NotBlank(message = "Informe o logradouro.")
    private String logradouro;

    @NotBlank(message = "Informe o número.")
    private String numero;

    private String complemento;

    @NotBlank(message = "Informe o bairro.")
    private String bairro;

    @NotBlank(message = "Informe a cidade.")
    private String cidade;

    @NotBlank(message = "Informe a UF.")
    @Size(min = 2, max = 2, message = "A UF deve possuir duas letras.")
    private String uf;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
