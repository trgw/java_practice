//https://ksuap.github.io/2016spring/library/index.html
//図書館システムの作成

package pack;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Map;
import java.util.TreeMap;

public class LibrarySystem {

    private List<Book> shelf = new ArrayList<Book>();
    private String shelfFile = "books.csv";
    private UserManager manager = new UserManager();
    private String usersFile = "users.csv";
    private Map<Book, List<History>> historyMap = new HashMap<Book, List<History>>();
    private List<History> cannotLend = new ArrayList<History>();

    public LibrarySystem(){
        this.printWelcomeMessage();
    }

    void run(String[] args){
        // 本の登録 （books.csv から
        addBooks(shelf);

        // レンタル者の登録
        registerUsers();

        // レンタル履歴の登録
        runLend();

        // レンタル履歴の表示
        for(Map.Entry<Book, List<History>> entry: historyMap.entrySet()){
            Book key = entry.getKey();
            List<History> value = entry.getValue();
            printBook(key);
            printHistoryList(value);
        }

        System.out.println();

        // コマンドライン引数から履歴を表示する
        // args[0] が本のタイトル、args[1] がレンタル者の名前
        // 本のタイトルだけで検索することも可能（引数が一つの場合）
        Book inputBook = new Book();
        if (args.length == 0) {
            inputBook = null;
        } else {
            inputBook.title = args[0];
        }

        User inputUser = new User();
        if (args.length == 0 || args.length == 1) {
            inputUser = null;
        } else {
            inputUser.name = args[1];
        }

        // Book も User も null だったとき全履歴を表示
        if (args.length == 0){
            System.out.println("上が全履歴です.");
        } else {
            printHistoryList(findHistory(inputBook, inputUser));
        }

        System.out.println("\n以下のレンタルリクエストは本が貸し出し中だったため拒否されました.");
        printHistoryList(cannotLend);
    }

    void printWelcomeMessage(){
        System.out.println("ようこそ図書館システムへ.\n本の題名と使用者の名前を入力することでレンタル履歴を表示することができます.\nスペース区切りで入力してください. 本の題名だけで入力することもできます.\n");
    }

    // 自分で作成した Book 型のオブジェクトを生成
    public Book createBook(String title, String authors, String publisher, Integer publishYear){
        Book book = new Book();
        book.title = title;
        book.authors = authors;
        book.publisher = publisher;
        book.publishYear = publishYear;
        return book;
    }

    // フィールドである shelf 内にある本を表示
    void list() {
        for(Book book: shelf) {
            this.printBook(book);
        }
    }

    void printBook(Book book){
        System.out.printf("%s (%s) %s, %d%n", book.title, book.authors, book.publisher, book.publishYear);
    }

    void addBooks(List<Book> shelf){
        LibraryUtil util = new LibraryUtil();
        List<Book> books = util.readFromFile(shelfFile);
        for(Integer i = 0; i < books.size(); i++){
            shelf.add(books.get(i));
        }
    }

    // 不使用
    Book find(String title){
        for(Book book: shelf) {
            if (Objects.equals(title, book.title)) {
                return book;
            }
        }
        return null;
    }

    // 不使用
    List<Book> findAnd(String title, String authors, String publisher, Integer publishYear){
        List<Book> result = new ArrayList<Book>();
 
        for(Book book: shelf){
            if(this.isMatchAnd(book, title, authors, publisher, publishYear)) {
                result.add(book);
            }
        }
        return result;
    }

    // 不使用
    Boolean isMatchAnd(Book book, String title, String authors, String publisher, Integer publishYear){
        if ((Objects.equals(book.title, title)) == false) {
    		return false;
    	} else {
    		if ((Objects.equals(book.authors, authors)) == false) {
    			return false;
    		} else {
    			if ((Objects.equals(book.publisher, publisher)) == false) {
    				return false;
    			} else {
    				if ((Objects.equals(book.publishYear, publishYear)) == false) {
    					return false;
    				} else {
    					return true;
    				}
    			}
    		}
    	}
    }

    // 不使用
    void findAndPrint(String title, String authors, String publisher, Integer publishYear){
        List<Book> result = new ArrayList<Book>();
        result = findAnd(title, authors, publisher, publishYear);
        for (int i = 0; i < result.size(); i++){
            printBook(result.get(i));
        }
    }

    // 不使用
    List<Book> findOr(String title, String authors, String publisher, Integer publishYear){
        List<Book> result = new ArrayList<Book>();
 
        for(Book book: shelf){
            if(this.isMatchOr(book, title, authors, publisher, publishYear)) {
                result.add(book);
            }
        }
        return result;
    }

    // 不使用
    Boolean isMatchOr(Book book, String title, String authors, String publisher, Integer publishYear){
        if ((Objects.equals(book.title, title))
                || (Objects.equals(book.authors, authors))
                || (Objects.equals(book.publisher, publisher))
                || (Objects.equals(book.publishYear, publishYear))){
                    return true;
                } else {
                    return false;
                }
    }

    // 不使用
    void findOrPrint(String title, String authors, String publisher, Integer publishYear){
        List<Book> result = new ArrayList<Book>();
        result = findOr(title, authors, publisher, publishYear);
        for (int i = 0; i < result.size(); i++){
            printBook(result.get(i));
        }
    }

    // 不使用
    void remove(Book book){
        shelf.remove(book);
    }

    // 不使用
    void remove2(String title, String author, String publisher, Integer publishYear){
        Book book = createBook(title, author, publisher, publishYear);
        shelf.remove(book);
    }

    // レンタル者の登録
    void registerUsers(){
        LibraryUtil util = new LibraryUtil();
        List<User> users = util.readFromUsersfile(usersFile);
        for(Integer i = 0; i < users.size(); i++){
            manager.add(users.get(i));
        }
    }

    History createHistory(User user, Book book){
        History history = new History();
        history.user = user;
        history.book = book;
        history.lendDate = new Date();
        return history;
    }

    Boolean registerHistory(History history){
        List<History> histories = historyMap.get(history.book);
        if(histories == null){
            histories = new ArrayList<History>();
            historyMap.put(history.book, histories);
        }
        if(this.canLend(history, histories)){
            histories.add(history);
            return true;
        }
        return false;
    }

    Boolean canLend(History history, List<History> histories){
        if(!shelf.contains(history.book)){
            return false;
        }
        if(histories.size() > 0){
            History lastHistory = histories.get(histories.size() - 1);
            if(lastHistory.isLent()){
                return false;
            }
        }
        return true;
    }

    // レンタル履歴（自分で作った History 型）の作成
    // 借りれなかった場合、その履歴を記録するリスト cannnotLend に追加
    History lend(User user, Book book) {
        if (registerHistory(createHistory(user, book)) == true){
            return createHistory(user, book);
        } else {
            cannotLend.add(createHistory(user, book));
            return null;
        }
    }

    void runLend(){
        lend(manager.users.get(0), shelf.get(0));
        lend(manager.users.get(1), shelf.get(0));
        lend(manager.users.get(2), shelf.get(1));
        lend(manager.users.get(3), shelf.get(2));
        lend(manager.users.get(4), shelf.get(3));
    }

    // 引数（ run 内でコマンドライン引数となる）から、
    // 本のタイトルかレンタル者が合っていれば History 型のリストを返す
    List<History> findHistory(Book book, User user){
        List<History> list = new ArrayList<History>();

        for(Map.Entry<Book, List<History>> entry: historyMap.entrySet()){
            Book key = entry.getKey();
            List<History> value = entry.getValue();

            List<History> prelist = new ArrayList<History>();

            if (book == null) {
                for (int i = 0; i < value.size(); i++){
                    prelist.add(value.get(i));
                }
            } else {
                for (int i = 0; i < value.size(); i++){
                    if (book.title.equals(key.title)){
                    prelist.add(value.get(i));
                    }
                }
            }

            if (user == null) {
                for (int i = 0; i < prelist.size(); i++){
                    list.add(prelist.get(i));
                }
            } else {
                for (int i = 0; i < prelist.size(); i++){
                    if (user.name.equals(prelist.get(i).user.name))
                    list.add(prelist.get(i));
                }
            }
        }
        return list;
    }

    void runFindHistory(List<History> historyList){
        for (int i = 0; i < historyList.size(); i++){
            printHistoryList(historyList);
        }
    }

    void printHistoryList(List<History> historyList){
        for (int i = 0; i < historyList.size(); i ++){
            System.out.println(historyList.get(i).user.name + " " + historyList.get(i).lendDate + " " + historyList.get(i).returnDate);
        }
    }

    public static void main (String[] args){
        LibrarySystem ls = new LibrarySystem();
        ls.run(args);
    }
}