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
     * Getter name attribute
     * @return the name of the node
     */
    public String getName() {
        return this.name;
    }

    // *** Methods **
    /**
     * Constructor for the node class
     * @param name name of the node
     */
    public Node(String name) {
        this.name = name;
    }
}
