//package com.nightingale.app.repository.impl.redis;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Repository;
//
//import com.nightingale.app.repository.ReservationRepository;
//
//@Repository
//public class ReservationRepositoryImpl implements ReservationRepository {
//
//
//	private RedisTemplate<String, Object> redisTemplate;
//	private HashOperations<String, Object, Object> hashOps;
//
//
//	@Autowired
//	public ReservationRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
//
//		this.redisTemplate = redisTemplate;
//	}
//
//	@PostConstruct
//	private void init() {
//
//		hashOps = redisTemplate.opsForHash();
//
//	}
//}
