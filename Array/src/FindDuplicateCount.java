import java.util.HashSet;

public class FindDuplicateCount {
 static class Node{
   int data;
   Node next;

   Node(int data){
     this.data=data;

   }

   public String toString(){
     return this.data+" ";
   }
 }
public static Node findDuplicateNode(Node n1){
   HashSet<Integer> set= new HashSet<>();
   Node temp=n1;
   while(temp!=null){
     if(set.contains(temp.data)){
       return temp;
     } else{
       set.add(temp.data);
     }
     temp=temp.next;
   }
   return null;
}
  public static Node findDuplicateNode(Node n1,int n){
    HashSet<Integer> set= new HashSet<>();
    Node temp=n1;
    while(temp!=null){
      if(set.contains(temp.data)){
        return temp;
      } else{
        set.add(temp.data);
      }
      temp=temp.next;
    }
    return null;
  }
  public static void main(String[] args) {
    Node n1= new Node(10);
    Node n2= new Node(20);
    Node n3= new Node(30);
    Node n4= new Node(40);

    n1.next=n2;
    n1.next.next=n3;
    n3.next=n4;

    Node temp=n1;
    while (temp !=null){
      System.out.println(temp.data);
      temp=temp.next;
    }
    Node duplicate=findDuplicateNode(n1);
    System.out.println(duplicate);

  }
}
