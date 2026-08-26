public class LinkedList {
  static class Node {
    int data;
    Node next;

    Node(int data) {
      this.data = data;
      this.next = null;
    }
  }

  static Node insertAtStart(Node head, int data) {
   Node newNode=new Node(data);
   newNode.next=head;
   return newNode;
  }

  static Node insertAtEnd(Node head, int data) {
    Node newNode = new Node(data);
    //when LinkedList is empty
    if (head == null) {
      return newNode;
    }
    Node temp = head;
    while (temp.next != null) {
      temp = temp.next;
    }
    temp.next = newNode;
    return head;
  }

  static Node insertAtIndex(Node head, int index, int data) {
    Node newNode = new Node(data);
    if(index==0) {
      return insertAtStart(head,data);
    }
    if (head == null) {
      return null;
    }
    Node temp=head;
    int i=0;

    while(i<index-1 && temp.next!=null){
      temp=temp.next;
      i++;
    }

    if (i <index-1) {
      temp.next = newNode;
    } else {
      newNode.next=temp.next;
      temp.next=newNode;
    }

    return head;
  }

  static Node deleteAtStart(Node head) {
    if (head == null) {
      return null;
    }

    return head.next;
  }

  static Node deleteAtEnd(Node head) {
     if(head==null || head.next==null) return null;

     Node temp=head;
     while(temp.next.next!=null){
       temp=temp.next;
     }
     temp.next=null;
     return head;
  }

  static Node deleteByIndex(Node head, int index) {
      if(head == null ) return null;
      if(index==0) return deleteAtStart(head);

      Node temp=head;
      int i=0;
      while(i<index-1 && temp.next!=null){
        temp=temp.next;
        i++;
      }
      if(temp.next==null){
        return head;
      }
      temp.next=temp.next.next;
      return head;
    }


  static void display(Node head) {
    Node temp=head;
      while (temp!=null){
        System.out.print(temp.data + "  ");
        temp=temp.next;
      }
    System.out.println();
  }

  public static void main(String[] args) {
    Node head = new Node(10);
    head.next = new Node(20);
    head.next.next = new Node(30);
    head.next.next.next = new Node(40);

    display(head);  // 10 20 30 40

    head = insertAtStart(head, 5);
    head = insertAtEnd(head, 67);
    head = insertAtIndex(head, 3, 59);
    head = deleteAtStart(head);
    head = deleteAtEnd(head);
    head = deleteByIndex(head, 4);

    display(head);
  }
}
