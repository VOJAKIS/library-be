package sk.umb.example.library.borrowing.controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class BorrowingController {

    @GetMapping("api/borrowings")
    public List searchBorrowing(@RequestParam(required = false) Long borrowingID) {
        System.out.println("Searching for borrowing: " + borrowingID);
        return Collections.emptyList();
    }

    @GetMapping("api/borrowings/{borrowingID}")
    public void getBorrowing(@PathVariable Long borrowingID) {
        System.out.println("Retrieving details about borrowing with ID " + borrowingID);
    }

    @PostMapping("api/borrowings")
    public void createBorrowing() {
        System.out.println("Creating borrowing ...");
    }

    @PutMapping("api/borrowings/{borrowingID}")
    public void updateBorrowing(@PathVariable Long borrowingID) {
        System.out.println("Updating borrowing with ID" + borrowingID);
    }

    @DeleteMapping("api/borrowings/{categoryID}")
    public void deleteBorrowing(@PathVariable Long borrowingID) {
        System.out.println("Deleting borrowing with ID" + borrowingID);
    }

}
