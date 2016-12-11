/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 * Code from https://github.com/vsg/csv
 * @author Vitaliy Garnashevich
 */
public class CsvParserException extends RuntimeException {

    private static final long serialVersionUID = -5454738869227879452L;

    public CsvParserException(String msg) {
        super(msg);
    }

    public CsvParserException(String msg, Throwable cause) {
        super(msg, cause);
    }

}