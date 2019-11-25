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
public interface Crud {
    public void create();
    public void read();
    public void update(Integer id);
    public void delete(Integer id);
}
