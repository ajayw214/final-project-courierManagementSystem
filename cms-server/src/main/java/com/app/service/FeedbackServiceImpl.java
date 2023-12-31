package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ICustomerRepository;
import com.app.dao.IFeedbackRepository;
import com.app.dto.FeedbackDTO;
import com.app.entities.Customer;
import com.app.entities.Feedback;

@Service
@Transactional
public class FeedbackServiceImpl implements IFeedbackService {

	@Autowired
	private IFeedbackRepository feedbackRepo;
	
	@Autowired
	private ICustomerRepository customerRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<FeedbackDTO> getAllFeedbacks() {
		List<Feedback> feedbackList = feedbackRepo.findAll();
		List<FeedbackDTO> feedbackDto=new ArrayList<FeedbackDTO>();
		for(Feedback f1 : feedbackList ) 
			feedbackDto.add( mapper.map(f1, FeedbackDTO.class));
		return feedbackDto;
	}

//	@Override
//	public FeedbackEntity insertFeedbackDetails(String message, long id) {
//		FeedbackEntity transientFeedback=new FeedbackEntity();
//		transientFeedback.setMessage(message);
//		CustomerEntity customer = customerRepo.findById(id).get();
//		transientFeedback.setCustomerFeedback(customer);
//		return feedbackRepo.save(transientFeedback);
//	}
	
	@Override
	   public Feedback insertFeedbackDetails(long customerId, String message) {
	       Customer customer = customerRepo.findById(customerId).get();
//	     transientFeedback.setCustomerFeedback(customer);
	       return feedbackRepo.save(new Feedback(message, customer));
	   }

}
