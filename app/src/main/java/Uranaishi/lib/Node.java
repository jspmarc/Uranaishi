/**
 * Library bantuan untuk membuat elemen graph, yaitu nodes
 * Josep Marcello
 * 25 February 2020
 */

package Uranaishi.lib;

/**
 * Kelas yang merepresentasikan node (sudut) pada graf/elemen pada linked
 * list.
 * @author Josep Marcello
 * 25 Februari 2021
 */
public class Node {
    // *** attribute ***
    private String info;

    // *** Getters and setters ***
    /**
     * Getter info attribute
     * @return info (nama) node
     */
    public String getInfo() {
        return this.info;
    }

    // *** Methods **
    /**
     * Konstruktor untuk class Node
     * @param info isi (nama) node
     */
    public Node(String info) {
        this.info = info;
    }
}
