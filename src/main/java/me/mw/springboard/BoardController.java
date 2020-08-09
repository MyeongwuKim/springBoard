package me.mw.springboard;

import me.mw.springboard.board.BoardInfo;
import me.mw.springboard.board.BoardSearch;
import me.mw.springboard.user.Account;
import me.mw.springboard.user.AccountList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class BoardController {

    @Autowired
    BoardInfo boardInfo;

    @Autowired
    JdbcController jdbcController;

    @Autowired
    FileService fileService;


    @PostMapping("/modifyOk")
    public String modifyOk(@ModelAttribute Account account,@RequestParam int titleNumber,
                           MultipartFile file) throws IOException {
        if(!file.isEmpty()) {
            Account myAccount = jdbcController.findAccountByTitleNumber(titleNumber);
            String filename=fileService.modifyFile(file, titleNumber, myAccount.getFilename());
            account.setFilename(filename);
        }
        account.setTitleNumber(titleNumber);
        jdbcController.modifyAccountData(account);
        return "redirect:/contentView?titleNumber="+titleNumber;
    }
    @GetMapping("/contentView/modify")
    public String modify(Model model, @RequestParam int titleNumber){
        Account account = jdbcController.findAccountByTitleNumber(titleNumber);
        model.addAttribute("account",account);
        model.addAttribute("titleNumber",titleNumber);
        model.addAttribute("boardInfo",boardInfo);
        return "modify";
    }

    @GetMapping("/deleteOk")
    public String deleteOk(@RequestParam int titleNumber){
        Account account=jdbcController.findAccountByTitleNumber(titleNumber);
        jdbcController.deleteDataByTitleNumber(titleNumber);
        return "redirect:/boardView/1";
    }

    @GetMapping("/checkPassword")
    public String checkPassword(){
        return "checkPassword";
    }

    @GetMapping("/searchView/{pageNumber}")
    public String searchView(Model model, @ModelAttribute BoardSearch boardSearch,
                         @PathVariable int pageNumber){

        String searchText = boardSearch.getSearchText();
        ArrayList<Account> searchAccountList=new ArrayList<>();
        AccountList accountList = new AccountList();

        for(int i=0; i<jdbcController.getAccountData().size();i++){
            Account account=jdbcController.getAccountData().get(i);
            accountList.setAccountList(account);
        }
        if(boardSearch.getSearchTarget().equals("id")){
            for(int i=0; i<accountList.getAccountList().size(); i++){
                Account account = accountList.getAccountInfo(i);
                if(account.getId().contains(searchText)){
                    searchAccountList.add(account);
                }
            }
        }
        else if(boardSearch.getSearchTarget().equals("title")){
            for(int i=0; i<accountList.getAccountList().size(); i++){
                Account account =
                        accountList.getAccountInfo(i);
                if(account.getTitle().contains(searchText)){
                    searchAccountList.add(account);
                }
            }
        }

        int maxPage=(accountList.getAccountList().size()-1)/5;
        boardInfo.setMaxPage(maxPage+1);
        boardInfo.setCurrentPage(pageNumber);
        model.addAttribute("boardInfo",boardInfo);
        model.addAttribute("searchAccountList",searchAccountList);
        model.addAttribute("boardSearch",boardSearch);
        return "searchView";
    }

    @GetMapping("/contentView")
    public String contentView(Model model, @RequestParam int titleNumber){
        Account account = jdbcController.findAccountByTitleNumber(titleNumber);
        model.addAttribute("account",account);
        model.addAttribute("titleNumber",titleNumber);
        model.addAttribute("boardInfo",boardInfo);
        return "contentView";
    }

    @PostMapping("/submit")
    public String submit(@Valid @ModelAttribute Account account,
                         BindingResult bindingResult, MultipartFile file, HttpServletRequest request) throws IOException {
        //set Date
        if(!bindingResult.hasErrors()){
            SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd" + " HH:mm:ss");
            Date date1 = new Date();
            String formatDate=format1.format(date1);
            account.setDate(formatDate);
            account.setTitleNumber(jdbcController.getRowIndexCount());

            if(!file.isEmpty()) {
                String filename = fileService.uploadFile(file, account.getTitleNumber(),request);
                account.setFilename(filename);
            }
            else {
                account.setFilename("null");
            }

            jdbcController.setAccountData(account);
        }
        return "redirect:/boardView/1";
    }

    @GetMapping("/write")
    public String write(){
        return "write";
    }

    @GetMapping("/boardView/{pageNumber}")
    public String boardView(Model model, @PathVariable int pageNumber) {
        //boardInfo
        AccountList accountList = new AccountList();
        for(int i=0; i<jdbcController.getAccountData().size();i++){
            Account account=jdbcController.getAccountData().get(i);
            accountList.setAccountList(account);
        }

        int maxPage=(accountList.getAccountList().size()-1)/5;
        boardInfo.setMaxPage(maxPage+1);
        boardInfo.setCurrentPage(pageNumber);
        model.addAttribute("boardInfo",boardInfo);
        model.addAttribute("accountList",accountList.getAccountList());

        return "boardView";
    }
}
