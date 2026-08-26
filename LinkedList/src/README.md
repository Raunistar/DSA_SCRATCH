# Linked List — Java DSA Notes

> Simple notes for understanding Linked List from scratch and revising it quickly.

---

## 1. What is a Linked List?

A Linked List is a collection of **nodes**. Every node stores:

- `data` → the actual value
- `next` → reference to the next node

```text
10 → 20 → 30 → 40 → null
```

Think of every node like:

```text
┌──────────┬──────────┐
│   data   │   next   │
└──────────┴──────────┘
```

`next` tells us where the next node is.

---

## 2. Node in Java

```java
static class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}
```

### Simple meaning

```java
int data;
```

Stores the value.

```java
Node next;
```

Stores the reference of another `Node`.

```java
this.next = null;
```

Initially, the node isn't connected to anything.

---

## 3. Creating and Linking Nodes

```java
Node n1 = new Node(10);
Node n2 = new Node(20);
Node n3 = new Node(30);
```

Initially they are separate:

```text
n1 → 10 → null
n2 → 20 → null
n3 → 30 → null
```

Connect them:

```java
n1.next = n2;
n2.next = n3;
```

Now:

```text
n1
 ↓
10 → 20 → 30 → null
      ↑     ↑
     n2    n3
```

---

## 4. Head

`head` points to the first node.

```java
Node head = n1;
```

```text
head
 ↓
10 → 20 → 30 → null
```

The important thing is: **if you have `head`, you can reach the whole list.**

---

## 5. Temp

We normally don't move `head` while traversing. Instead we use a temporary reference:

```java
Node temp = head;
```

Then:

```java
temp = temp.next;
```

moves `temp` to the next node.

```text
head
 ↓
10 → 20 → 30
      ↑
     temp
```

Think:

- `head` = starting point
- `temp` = reference we move around

---

# 6. Traversal

To visit every node:

```java
Node temp = head;

while (temp != null) {
    System.out.println(temp.data);
    temp = temp.next;
}
```

For:

```text
10 → 20 → 30 → null
```

`temp` moves:

```text
10 → 20 → 30 → null
↑
```

then:

```text
10 → 20 → 30 → null
     ↑
```

then:

```text
10 → 20 → 30 → null
          ↑
```

then `temp == null`, so the loop stops.

### Remember

```java
while (temp != null)
```

is the normal traversal condition.

---

# 7. Insert at Beginning

Suppose:

```text
head
 ↓
10 → 20 → 30 → null
```

We want to insert `5`.

Create the node:

```java
Node newNode = new Node(5);
```

### Step 1: Connect new node to old head

```java
newNode.next = head;
```

Now:

```text
5 → 10 → 20 → 30 → null
    ↑
   head
```

### Step 2: Make new node the head

```java
head = newNode;
```

Final:

```text
head
 ↓
5 → 10 → 20 → 30 → null
```

### Code

```java
static Node insertAtStart(Node head, int data) {
    Node newNode = new Node(data);

    newNode.next = head;

    return newNode;
}
```

Call it like:

```java
head = insertAtStart(head, 5);
```

**Time:** `O(1)`  
**Space:** `O(1)`

---

# 8. Insert at End

Suppose:

```text
10 → 20 → 30 → null
```

We want:

```text
10 → 20 → 30 → 40 → null
```

Create:

```java
Node newNode = new Node(40);
```

Find the last node:

```java
Node temp = head;

while (temp.next != null) {
    temp = temp.next;
}
```

Now `temp` is at the last node:

```text
10 → 20 → 30 → null
          ↑
         temp
```

Connect the new node:

```java
temp.next = newNode;
```

### Code

```java
static Node insertAtEnd(Node head, int data) {
    Node newNode = new Node(data);

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
```

Call:

```java
head = insertAtEnd(head, 40);
```

**Time:** `O(n)`  
**Space:** `O(1)`

---

# 9. Insert at Index

We use **zero-based indexing**:

```text
Index:  0    1    2    3
        ↓    ↓    ↓    ↓
        10 → 20 → 30 → 40
```

Suppose we insert `25` at index `2`:

```text
10 → 20 → 25 → 30 → 40
```

We first reach the node **just before** the insertion position:

```text
10 → 20 → 30 → 40
     ↑
    temp
```

Then:

```java
newNode.next = temp.next;
temp.next = newNode;
```

Result:

```text
10 → 20 → 25 → 30 → 40
```

### Code

```java
static Node insertAtIndex(Node head, int index, int data) {

    if (index == 0) {
        return insertAtStart(head, data);
    }

    if (head == null) {
        return null;
    }

    Node newNode = new Node(data);
    Node temp = head;
    int i = 0;

    while (i < index - 1) {
        temp = temp.next;
        i++;
    }

    newNode.next = temp.next;
    temp.next = newNode;

    return head;
}
```

Call:

```java
head = insertAtIndex(head, 2, 25);
```

### Important pattern

```java
newNode.next = temp.next;
temp.next = newNode;
```

**Order matters.** First save the old connection, then replace it.

> We intentionally did not add full index validation yet. First make the pointer logic strong.

---

# 10. Delete at Beginning

Suppose:

```text
head
 ↓
10 → 20 → 30 → null
```

We want to remove `10`.

Just move `head` forward:

```java
head = head.next;
```

Result:

```text
head
 ↓
20 → 30 → null
```

### Code

```java
static Node deleteAtStart(Node head) {

    if (head == null) {
        return null;
    }

    return head.next;
}
```

Call:

```java
head = deleteAtStart(head);
```

**Time:** `O(1)`  
**Space:** `O(1)`

---

# 11. Delete at End

Suppose:

```text
10 → 20 → 30 → 40 → null
```

We want to remove `40`.

We need to reach the **second-last node**:

```text
10 → 20 → 30 → 40
          ↑
         temp
```

Then:

```java
temp.next = null;
```

Result:

```text
10 → 20 → 30 → null
```

To stop at the second-last node:

```java
while (temp.next.next != null) {
    temp = temp.next;
}
```

### Code

```java
static Node deleteAtEnd(Node head) {

    if (head == null) {
        return null;
    }

    if (head.next == null) {
        return null;
    }

    Node temp = head;

    while (temp.next.next != null) {
        temp = temp.next;
    }

    temp.next = null;

    return head;
}
```

### Why `temp.next.next`?

Because we don't want `temp` to reach the last node. We want it to stop **one node before the last**.

**Time:** `O(n)`  
**Space:** `O(1)`

---

# 12. Delete at Index

Suppose:

```text
Index:  0    1    2    3
        ↓    ↓    ↓    ↓
        10 → 20 → 30 → 40
```

Delete index `2`.

Expected:

```text
10 → 20 → 40
```

Again, reach the node **just before** the target:

```text
10 → 20 → 30 → 40
     ↑     ↑
    temp  delete
```

Now skip the target:

```java
temp.next = temp.next.next;
```

Result:

```text
10 → 20 → 40
```

### Code

```java
static Node deleteAtIndex(Node head, int index) {

    if (index == 0) {
        return deleteAtStart(head);
    }

    if (head == null) {
        return null;
    }

    Node temp = head;
    int i = 0;

    while (i < index - 1) {
        temp = temp.next;
        i++;
    }

    temp.next = temp.next.next;

    return head;
}
```

### Main idea

We don't manually destroy the node. We simply **skip it**.

```text
Before:

20 → 30 → 40

After:

20 ─────→ 40
      30
    skipped
```

**Time:** `O(n)`  
**Space:** `O(1)`

---

# 13. The Important Patterns

If these become natural, most basic Linked List operations become easy.

### Move to next node

```java
temp = temp.next;
```

### Insert between two nodes

```java
newNode.next = temp.next;
temp.next = newNode;
```

Think:

```text
A → B

A → NEW → B
```

### Delete one node

```java
temp.next = temp.next.next;
```

Think:

```text
A → B → C

A ─────→ C
    B skipped
```

### Move head forward

```java
head = head.next;
```

### New node becomes head

```java
return newNode;
```

---

# 14. `head` vs `temp`

### `head`

The beginning of the list:

```text
head
 ↓
10 → 20 → 30
```

### `temp`

A moving reference:

```text
head
 ↓
10 → 20 → 30
      ↑
     temp
```

Usually:

```java
Node temp = head;
```

Then:

```java
temp = temp.next;
```

### Simple rule

> **Keep `head` safe. Move `temp`.**

Unless the operation itself is supposed to change the head.

---

# 15. Why Do We Return `head`?

Some operations change the head.

### Insert at start

Before:

```text
10 → 20
```

After:

```text
5 → 10 → 20
↑
new head
```

So:

```java
head = insertAtStart(head, 5);
```

### Delete at start

Before:

```text
10 → 20 → 30
```

After:

```text
20 → 30
↑
new head
```

So:

```java
head = deleteAtStart(head);
```

This is because Java passes the **reference value** to the method. Reassigning the method's local `head` does not reassign the caller's `head`.

---

# 16. Java Reference Concept

Suppose:

```java
Node root = n1;
Node temp = n1;
```

Both references point to the same object:

```text
root ──┐
       ↓
      [10]
       ↑
temp ──┘
```

If we do:

```java
root = newNode;
```

only the `root` reference changes.

But:

```java
root.data = 50;
```

changes the actual Node object.

### Remember

```text
root = newNode
→ reference changes

root.data = 50
→ object changes
```

This is the reason we return the new head from methods that change the head.

---

# 17. Empty List

An empty list:

```text
head → null
```

Be careful with:

```java
head.next
```

when:

```java
head == null
```

That causes `NullPointerException`.

That's why methods often begin with:

```java
if (head == null) {
    return null;
}
```

---

# 18. Common Mistakes

## Mistake 1 — Moving `head` during traversal

Avoid:

```java
while (head != null) {
    head = head.next;
}
```

Prefer:

```java
Node temp = head;

while (temp != null) {
    temp = temp.next;
}
```

---

## Mistake 2 — Wrong insertion order

Wrong:

```java
temp.next = newNode;
newNode.next = temp.next;
```

The old `temp.next` connection is lost.

Correct:

```java
newNode.next = temp.next;
temp.next = newNode;
```

---

## Mistake 3 — Thinking `temp = null` deletes a node

This:

```java
temp = null;
```

only changes `temp`.

It doesn't disconnect the node from the actual list.

For deleting the last node:

```java
temp.next = null;
```

---

## Mistake 4 — Deleting an index with `temp.next = null`

This:

```java
temp.next = null;
```

cuts off everything after `temp`.

To remove only the next node:

```java
temp.next = temp.next.next;
```

---

# 19. Complexity Cheat Sheet

| Operation | Time | Space |
|---|---:|---:|
| Traversal | O(n) | O(1) |
| Insert at start | O(1) | O(1) |
| Insert at end | O(n) | O(1) |
| Insert at index | O(n) | O(1) |
| Delete at start | O(1) | O(1) |
| Delete at end | O(n) | O(1) |
| Delete at index | O(n) | O(1) |

Why is end insertion/deletion `O(n)`?

Because with only a `head`, we have to walk through the list to reach the required position.

---

# 20. Quick Revision — 2 Minutes

If you have very little time, remember this:

```text
head
 ↓
10 → 20 → 30 → null
```

### Traverse

```java
temp = temp.next;
```

### Insert at start

```java
newNode.next = head;
head = newNode;
```

### Insert at end

```java
while (temp.next != null)
    temp = temp.next;

temp.next = newNode;
```

### Insert at index

```java
newNode.next = temp.next;
temp.next = newNode;
```

### Delete start

```java
head = head.next;
```

### Delete end

```java
temp.next = null;
```

`temp` should be the second-last node.

### Delete at index

```java
temp.next = temp.next.next;
```

---

# 21. One Mental Picture

Think of nodes as people holding hands:

```text
10 🤝 20 🤝 30 🤝 40
```

### Insert

Put someone between two people:

```text
10 🤝 20 🤝 30

       ↓ insert 25

10 🤝 20 🤝 25 🤝 30
```

### Delete

Make the previous person hold the next person's hand:

```text
10 🤝 20 🤝 30

       ↓ delete 20

10 🤝 30
```

That's basically what we are doing with `next`.

---

# 22. What We Have Covered

- [x] Node
- [x] `data`
- [x] `next`
- [x] Creating nodes
- [x] Linking nodes
- [x] `head`
- [x] `temp`
- [x] Traversal
- [x] Insert at beginning
- [x] Insert at end
- [x] Insert at index
- [x] Delete at beginning
- [x] Delete at end
- [x] Delete at index
- [x] Empty list basics
- [x] Java reference concept
- [x] Time and space complexity

---

# 23. What's Next?

After these basics, move to:

1. Find length of Linked List
2. Search an element
3. Find middle element
4. Reverse Linked List ⭐
5. Find nth node from end
6. Remove duplicates
7. Detect cycle
8. Find starting point of cycle
9. Check palindrome
10. Merge two sorted Linked Lists

### Next important problem

> **Reverse a Linked List**

It will make the `next` reference concept much stronger.

---

# Final Mental Model

Don't memorize every function separately.

Remember:

```text
Node = data + next

head = first node

temp = moving reference
```

### Move

```java
temp = temp.next;
```

### Insert

```java
newNode.next = temp.next;
temp.next = newNode;
```

### Delete

```java
temp.next = temp.next.next;
```

### Move head

```java
head = head.next;
```

### New head

```java
return newNode;
```

> **Linked List ka main idea:** nodes ko physically idhar-udhar nahi karte. `next` references ko change karke nodes ki chain change karte hain.
