/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web_protocols_lab_1;
/**
 *
 * @author DeGianMind.0
 */
public class List {
	private int length = 0;
	private Node First = new Node("");
	private Node Last = First;
	private Node Current;

	private Node retelem (int num) {
		Node buff = First;
		if ((num > 0) & (num <= length)) {
			for(int i = 0; i < num; i++){
				buff = buff.next;
			}
		}
		return buff;
	}

	public void addNode (String e) {
		Last.next = new Node(e);
		Last = Last.next;
		length++;
	}

	public  String delNode (int num) {
		if ((num <= 0) | (num > length)) {
			return "Наибольший элемент списка:" + length;
		}
		Current = retelem(num - 1);
		Current.next = Current.next.next;
		length--;
		return "Удаление произведено!";
	}

	public String[] getelem () {
		Current = First;
		String[] s = new String[length];
		for (int i = 0; i < length; i++) {
			Current = Current.next;
			s[i] = Current.setelem();
		}
		return s;
	}

	public void sort () {
		List BList = new List();
		BList.length = length;
		BList.First.next = First.next;
		String max;
		while (length > 1) {
			int imax = 1;
			Current = First.next;
			max = Current.setelem();
			for (int j = 1; j < length; j++) {
				if (max.compareTo(Current.next.setelem()) < 0 ) {
					imax = j+1;
					if (BList.length == length) {
						BList.First.next = Current.next;
					}
				}
				Current = Current.next;
			}
			BList.Last.next = retelem(imax);
			BList.Last = BList.Last.next;
			delNode(imax);
		}
		First.next = BList.First.next;
		length = BList.length;
	}
}
