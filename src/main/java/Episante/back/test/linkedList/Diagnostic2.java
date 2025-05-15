package Episante.back.test.linkedList;

public class Diagnostic2 {
    public static void main(String[] args) {
        Node node1 = new Node("Do you have a fever ?") ;
        Node node2 = new Node("Do you have fatigue ?") ;
        Node node3 = new Node("Do you have a covide ?") ;

        // so in this point i have        |str1, null |       |str2, null |         |str3, null |

        LinkedList linkedList = new LinkedList() ;
        linkedList.head = node1 ;

        node1.next = node2 ;
        node2.next = node3 ;

        // now we have       |Head=node1|---->|node2|---->|node3|

        Node currentNode = linkedList.head ;
        while (currentNode != null){
            System.out.print(currentNode.nodeText + "------>");
            currentNode = currentNode.next ;
        }


    }

}
