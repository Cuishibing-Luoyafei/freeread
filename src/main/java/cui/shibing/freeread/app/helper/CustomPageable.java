package cui.shibing.freeread.app.helper;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class CustomPageable implements Pageable{

	private int pageNumber;
	
	private int pageSize;
	
	private int offset;
	
	public CustomPageable(int pageNumber,int pageSize){
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.offset = (pageNumber - 1) * pageSize;
	}
	
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPageNumber() {
		// TODO Auto-generated method stub
		return pageNumber;
	}

	public int getPageSize() {
		// TODO Auto-generated method stub
		return pageSize;
	}

	public int getOffset() {
		// TODO Auto-generated method stub
		return offset;
	}

	public Sort getSort() {
		// TODO Auto-generated method stub
		return null;
	}

	public Pageable next() {
		// TODO Auto-generated method stub
		return null;
	}

	public Pageable previousOrFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	public Pageable first() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return false;
	}

}
