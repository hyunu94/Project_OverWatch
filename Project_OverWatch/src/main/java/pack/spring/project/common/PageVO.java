package pack.spring.project.common;

public class PageVO {
	private int totalRecord;
	private int nowPage ;
	private int totalPage;
	private int numPerPage;
	private int nowBlock;
	private int pagePerBlock;
	private int totalBlock;

	public PageVO() {
		super();
	}

	public PageVO(int totalRecord, int nowPage, int totalPage, int numPerPage, int nowBlock, int pagePerBlock,
			int totalBlock) {
		super();
		this.totalRecord = totalRecord;
		this.nowPage = nowPage;
		this.totalPage = totalPage;
		this.numPerPage = numPerPage;
		this.nowBlock = nowBlock;
		this.pagePerBlock = pagePerBlock;
		this.totalBlock = totalBlock;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public int getNowBlock() {
		return nowBlock;
	}

	public void setNowBlock(int nowBlock) {
		this.nowBlock = nowBlock;
	}

	public int getPagePerBlock() {
		return pagePerBlock;
	}

	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}

	public int getTotalBlock() {
		return totalBlock;
	}

	public void setTotalBlock(int totalBlock) {
		this.totalBlock = totalBlock;
	}

	@Override
	public String toString() {
		return "PageVO [totalRecord=" + totalRecord + ", nowPage=" + nowPage + ", totalPage=" + totalPage
				+ ", numPerPage=" + numPerPage + ", nowBlock=" + nowBlock + ", pagePerBlock=" + pagePerBlock
				+ ", totalBlock=" + totalBlock + "]";
	}

}
