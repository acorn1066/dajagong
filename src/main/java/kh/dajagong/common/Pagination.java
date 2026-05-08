package kh.dajagong.common; 

public class Pagination {
    public static PageInfo getPageInfo(int currentPage, int listCount, int boardLimit) {
        int pageLimit = 10;
        
     
        int maxPage = (int) Math.ceil((double)listCount / boardLimit);
        
        
        if(maxPage == 0) {
            maxPage = 1;
        }

        if (currentPage >maxPage) currentPage = maxPage;
        
        if (currentPage < 1) currentPage = 1;
        
        int startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
        
       
        int endPage = startPage + pageLimit - 1;
        
        
        if(maxPage < endPage) {
            endPage = maxPage;
        }
        
        return new PageInfo(currentPage, listCount, pageLimit, maxPage, startPage, endPage, boardLimit);
    }
}