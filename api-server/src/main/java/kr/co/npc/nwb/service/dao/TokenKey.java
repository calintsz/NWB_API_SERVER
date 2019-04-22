package kr.co.npc.nwb.service.dao;

import kr.co.npc.nwb.core.KeyMaker;
import redis.clients.util.MurmurHash;

public class TokenKey implements KeyMaker {
    static final int SEED_MURMURHASH = 0x1234ABCD;

    private String userGwId;
    private String userSysId;
    private long issueDate;

    /**
     * 키 메이커 클래스를 위한 생성자.
     * 
     * @param userGwId
     *            키 생성을 위한 인덱스 값
     * @param userSysId
     *            키 생성을 위한 인덱스 값
     * @param issueDate
     */
    public TokenKey(String userGwId, String userSysId, long issueDate) {
        this.userGwId = userGwId;
        this.issueDate = issueDate;
        this.userSysId = userSysId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.sf.redisbook.ch7.redislogger.KeyMaker#getKey()
     */
    @Override
    public String getKey() {
        String source = userGwId +userSysId+ String.valueOf(issueDate);
        return Long.toString(MurmurHash.hash64A(source.getBytes(), SEED_MURMURHASH), 16);
    }
}