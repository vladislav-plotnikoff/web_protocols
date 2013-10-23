/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web_protocols_lab_1;

/**
 *
 * @author DeGianMind.0
 */
public class Node {
	private String elem;
	Node next;

	public Node (String e) {
		elem = e;
		next = null;
	}

	public String setelem () {
		return elem;    
	}

	public  void getelem (String e) {
		elem = e;
	}
}
