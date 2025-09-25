/**
 * كلاس يمثل العقدة (Node) في القائمة المترابطة.
 * نستخدم النوع العام <T> لجعله يعمل مع أي نوع من البيانات (String, Integer, etc.).
 */
class Node<T> {
    T data;         // البيانات التي تخزنها العقدة
    Node<T> next;   // مؤشر يشير إلى العقدة التالية

    /**
     * المُنشئ (Constructor) لإنشاء عقدة جديدة.
     * @param data البيانات التي سيتم تخزينها.
     */
    public Node(T data) {
        this.data = data;
        this.next = null; // مبدئيًا، العقدة الجديدة لا تشير إلى أي شيء
    }
}

/**
 * كلاس يمثل القائمة المترابطة (LinkedList) نفسها.
 */
class LinkedList<T> {
    Node<T> head; // رأس القائمة، وهو نقطة البداية

    /**
     * دالة لإضافة عنصر جديد في نهاية القائمة.
     * @param data البيانات المراد إضافتها.
     */
    public void append(T data) {
        Node<T> newNode = new Node<>(data);

        // إذا كانت القائمة فارغة، العقدة الجديدة تصبح هي الرأس
        if (head == null) {
            head = newNode;
            return;
        }

        // البحث عن آخر عقدة في القائمة
        Node<T> last = head;
        while (last.next != null) {
            last = last.next;
        }

        // ربط العقدة الجديدة بآخر عقدة
        last.next = newNode;
    }

    /**
     * دالة لإضافة عنصر جديد في بداية القائمة.
     * @param data البيانات المراد إضافتها.
     */
    public void prepend(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head; // العقدة الجديدة تشير إلى الرأس القديم
        head = newNode;      // العقدة الجديدة تصبح هي الرأس الجديد
    }

    /**
     * دالة لحذف أول عقدة تحتوي على بيانات معينة.
     * @param data البيانات المراد حذفها.
     */
    public void delete(T data) {
        Node<T> current = head;
        Node<T> prev = null;

        // الحالة 1: إذا كانت العقدة المراد حذفها هي الرأس
        if (current != null && current.data.equals(data)) {
            head = current.next; // تغيير الرأس
            return;
        }

        // الحالة 2: البحث عن العقدة في بقية القائمة
        while (current != null && !current.data.equals(data)) {
            prev = current;
            current = current.next;
        }

        // إذا تم العثور على العقدة
        if (current != null) {
            prev.next = current.next; // تجاوز العقدة الحالية لحذفها
        } else {
            System.out.println("خطأ: العنصر '" + data + "' غير موجود في القائمة.");
        }
    }
    
    /**
     * دالة لطباعة القائمة بشكل سهل القراءة.
     * تم إعادة تعريف دالة toString() الافتراضية.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data).append(" -> ");
            current = current.next;
        }
        sb.append("null");
        return sb.toString();
    }
}

// --- مثال على كيفية الاستخدام ---
public class LinkedListExample {
    public static void main(String[] args) {
        // إنشاء قائمة مترابطة جديدة (ستخزن نصوص String)
        LinkedList<String> llist = new LinkedList<>();

        // إضافة عناصر في نهاية القائمة
        llist.append("A");
        llist.append("B");
        llist.append("C");
        System.out.println("بعد إضافة A, B, C:");
        System.out.println(llist); // المخرجات: A -> B -> C -> null

        // إضافة عنصر في بداية القائمة
        llist.prepend("Start");
        System.out.println("\nبعد إضافة 'Start' في البداية:");
        System.out.println(llist); // المخرجات: Start -> A -> B -> C -> null

        // حذف عنصر من القائمة
        llist.delete("B");
        System.out.println("\nبعد حذف 'B':");
        System.out.println(llist); // المخرجات: Start -> A -> C -> null
        
        // محاولة حذف عنصر غير موجود
        System.out.println("\nمحاولة حذف عنصر غير موجود 'X':");
        llist.delete("X"); // المخرجات: خطأ: العنصر 'X' غير موجود في القائمة.
        System.out.println(llist);
    }
}