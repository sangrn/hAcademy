package service;

import java.util.List; 

import dao.ReplyDao;
import vo.Reply;

public class ReplyServiceImpl implements ReplyService {
	private ReplyDao dao = new ReplyDao();

	@Override
	public void write(Reply reply) {
		dao.insert(reply);
	}

	@Override
	public List<Reply> list(Long bno) {
		// TODO Auto-generated method stub
		return dao.list(bno);
	}

	@Override
	public Reply get(Long rno) {
		// TODO Auto-generated method stub
		return dao.select(rno);
	}

	@Override
	public void remove(Long rno) {
		// TODO Auto-generated method stub
		dao.delete(rno);
	}

}