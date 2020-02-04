package Model;

public class Page {
    private static int pageNo;
    //public Page(){
        //pageNo = 1;
    //}

    public void  setPageNo(int page){
        this.pageNo = page;
    }

    public int getPageNo(){
        return pageNo;
    }

    public  void setNextPage(){
        pageNo += 1;
    }

    public  void setPreviousPage(){
        pageNo -= 1;
    }
}
