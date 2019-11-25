/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surat.Config;

/**
 *
 * @author riefist
 */
public class Role {
    private String nama;
    private String id;
    
    public Role(String nama, String id) {
        this.nama = nama;
        this.id = id;
    }
    
    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.nama;
    }
    
}
