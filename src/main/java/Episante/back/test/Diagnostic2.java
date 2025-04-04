package Episante.back.test ;

public class Diagnostic2 {
    public static void main(String[] args) {
        Nodes node1 = new Nodes("Do you have a fever ?") ;
        Nodes node2 = new Nodes("Do you have fatigue ?") ;
        Nodes node3 = new Nodes("Do you have a covide ?") ;

        // so in this point i have        |str1, null |       |str2, null |         |str3, null |

        LinkedList linkedList = new LinkedList() ;
        linkedList.head = node1 ;

        node1.next = node2 ;
        node2.next = node3 ;

        // now we have       |Head=node1|---->|node2|---->|node3|

        Nodes currentNode = linkedList.head ;
        while (currentNode != null){
            System.out.print(currentNode.nodeText + "------>");
            currentNode = currentNode.next ;
        }


    }

}
