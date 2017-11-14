package com.goldencrow.android.bookinventory.utilities;

import com.goldencrow.android.bookinventory.entity.Book;
import com.goldencrow.android.bookinventory.entity.enums.BookType;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Philipp Hermüller
 * @version 12.11.2017
 */

public class BookInventoryUtility {

    /**
     * Return a list of books.
     * (This also includes the GenreTitles)
     *
     * @return  a list of all books (and genreTitles).
     */
    public static List<Book> getWebNovelsFromAPI() {
        List<Book> books = new LinkedList<>();

        books.add(
                new Book(BookType.GENRE_TITLE, "Xianxia", "")
        );
        books.add(
                new Book(BookType.BOOK_ITEM, "Library of Heaven's Path", "https://img.webnovel.com/bookcover/6831850602000905/300/300.jpg?v=1")
        );
        books.add(
                new Book(BookType.BOOK_ITEM, "True Martial World", "http://cdn.novelupdates.com/images/2016/04/640-1.jpg")
        );
        books.add(
                new Book(BookType.BOOK_ITEM, "Monster Paradise", "http://cdn.novelupdates.com/images/2017/11/Monster-Paradise.jpg")
        );
        books.add(
                new Book(BookType.BOOK_ITEM, "Ancient Godly Monarch", "http://cdn.novelupdates.com/images/2016/04/Ancient-Godly-Monarch.jpg")
        );
        books.add(
                new Book(BookType.BOOK_ITEM, "The Unrivaled Tang Sect", "https://favoritenovels.com/NU/novels/douluo-dalu-2-the-unrivaled-tang-sect.jpg")
        );
        books.add(
                new Book(BookType.BOOK_ITEM, "The Great Ruler", "http://cdn.novelupdates.com/images/2015/07/thegreatruler.jpg")
        );
        books.add(
                new Book(BookType.BOOK_ITEM, "Perfect World", "http://qidian.qpic.cn/qdbimg/349573/2952453/300")
        );

        books.add(
                new Book(BookType.GENRE_TITLE, "Gaming", "")
        );
        books.add(
                new Book(BookType.BOOK_ITEM, "The Kings Avatar", "https://i.pinimg.com/736x/95/20/e5/9520e57c8f50f2b802514d719b1911d6--lord-the-kings-avatar.jpg")
        );

        books.add(
                new Book(BookType.GENRE_TITLE, "Xuanhuan", "")
        );
        books.add(
                new Book(BookType.BOOK_ITEM, "Library of Heaven's Path", "https://img.webnovel.com/bookcover/6831850602000905/300/300.jpg?v=1")
        );
        books.add(
                new Book(BookType.BOOK_ITEM, "True Martial World", "http://cdn.novelupdates.com/images/2016/04/640-1.jpg")
        );
        books.add(
                new Book(BookType.BOOK_ITEM, "Monster Paradise", "http://cdn.novelupdates.com/images/2017/11/Monster-Paradise.jpg")
        );
        books.add(
                new Book(BookType.BOOK_ITEM, "Ancient Godly Monarch", "http://cdn.novelupdates.com/images/2016/04/Ancient-Godly-Monarch.jpg")
        );
        books.add(
                new Book(BookType.BOOK_ITEM, "The Unrivaled Tang Sect", "https://favoritenovels.com/NU/novels/douluo-dalu-2-the-unrivaled-tang-sect.jpg")
        );
        books.add(
                new Book(BookType.BOOK_ITEM, "The Great Ruler", "http://cdn.novelupdates.com/images/2015/07/thegreatruler.jpg")
        );
        books.add(
                new Book(BookType.BOOK_ITEM, "Perfect World", "http://qidian.qpic.cn/qdbimg/349573/2952453/300")
        );

        books.add(
                new Book(BookType.GENRE_TITLE, "Gaming", "")
        );
        books.add(
                new Book(BookType.BOOK_ITEM, "The Kings Avatar", "https://i.pinimg.com/736x/95/20/e5/9520e57c8f50f2b802514d719b1911d6--lord-the-kings-avatar.jpg")
        );

        books.add(
                new Book(BookType.GENRE_TITLE, "Xuanhuan", "")
        );
        books.add(
                new Book(BookType.BOOK_ITEM, "Library of Heaven's Path", "https://img.webnovel.com/bookcover/6831850602000905/300/300.jpg?v=1")
        );
        books.add(
                new Book(BookType.BOOK_ITEM, "True Martial World", "http://cdn.novelupdates.com/images/2016/04/640-1.jpg")
        );
        books.add(
                new Book(BookType.BOOK_ITEM, "Monster Paradise", "http://cdn.novelupdates.com/images/2017/11/Monster-Paradise.jpg")
        );
        books.add(
                new Book(BookType.BOOK_ITEM, "Ancient Godly Monarch", "http://cdn.novelupdates.com/images/2016/04/Ancient-Godly-Monarch.jpg")
        );
        books.add(
                new Book(BookType.BOOK_ITEM, "The Unrivaled Tang Sect", "https://favoritenovels.com/NU/novels/douluo-dalu-2-the-unrivaled-tang-sect.jpg")
        );
        books.add(
                new Book(BookType.BOOK_ITEM, "The Great Ruler", "http://cdn.novelupdates.com/images/2015/07/thegreatruler.jpg")
        );
        books.add(
                new Book(BookType.BOOK_ITEM, "Perfect World", "http://qidian.qpic.cn/qdbimg/349573/2952453/300")
        );

        return books;
    }

}
