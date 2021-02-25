/**
 * Library bantuan untuk membuat elemen graph, yaitu nodes
 * Josep Marcello
 * 25 Februari 2020
 */

package Uranaishi.lib;

public class Node {
    // *** attribute ***
    private String name;

    // *** Getters and setters ***
    /**
     * Getter atribut name
     * @return atribut name
     */
    public String getName() {
        return this.name;
    }

    // *** Methods **
    /**
     * Konstruktor untuk class Node
     * @param name nama node
     */
    public Node(String name) {
        this.name = name;
    }
}
