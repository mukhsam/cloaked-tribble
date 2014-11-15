package example.entity.dao;

import java.util.ArrayList;

public class PagingList<E> extends ArrayList<E> {
	
	private boolean hasMore;

	public boolean isHasMore() {
		return hasMore;
	}

	public void setHasMore(boolean hasMore) {
		this.hasMore = hasMore;
	}
	

}
