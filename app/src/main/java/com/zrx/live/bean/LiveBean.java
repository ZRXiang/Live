package com.zrx.live.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiaoyuan on 17/2/28.
 * 直播的Bean类。由GsonFromat加载出来
 */

public class LiveBean implements Serializable {


    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1489060085463,"updated_at":1489060963928,"id":1157938031951875,"data":{"status":0,"live_name":"马泽宇直播","pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489070127924&di=1265b69c4092d0f9c4be4095eead6021&imgtype=0&src=http%3A%2F%2Fa.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fca1349540923dd54261fa262d309b3de9c82484f.jpg","live_type":0},"uid":1157677280460801,"user":{"user_data":{"user_name":"小源","avatar":"http://d.hiphotos.baidu.com/zhidao/wh%3D600%2C800/sign=44efbe491e30e924cff194377c38423e/dcc451da81cb39dbf51ac417d1160924aa18309c.jpg"},"id":1157677280460801,"created_at":1489044543163,"updated_at":1489044543225}},{"created_at":1489060118617,"updated_at":1489060972136,"id":1157938585600004,"data":{"status":0,"live_name":"杨文静直播","pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489070127921&di=b7193a4b0f083fb5e54310e4f2836f4f&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fdbb44aed2e738bd4770bcc30a38b87d6277ff98d.jpg","live_type":0},"uid":1157677280460801,"user":{"user_data":{"user_name":"小源","avatar":"http://d.hiphotos.baidu.com/zhidao/wh%3D600%2C800/sign=44efbe491e30e924cff194377c38423e/dcc451da81cb39dbf51ac417d1160924aa18309c.jpg"},"id":1157677280460801,"created_at":1489044543163,"updated_at":1489044543225}},{"created_at":1489060143902,"updated_at":1489060981240,"id":1157939005030405,"data":{"status":0,"live_name":"边翠霞直播","pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489070127919&di=703d167b664bd5fb15951419addb8464&imgtype=0&src=http%3A%2F%2Fa.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F7c1ed21b0ef41bd5254b96f553da81cb39db3d92.jpg","live_type":0},"uid":1157677280460801,"user":{"user_data":{"user_name":"小源","avatar":"http://d.hiphotos.baidu.com/zhidao/wh%3D600%2C800/sign=44efbe491e30e924cff194377c38423e/dcc451da81cb39dbf51ac417d1160924aa18309c.jpg"},"id":1157677280460801,"created_at":1489044543163,"updated_at":1489044543225}}]}
     * error_code : 0
     */

    private int error_code;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean implements Serializable{
        /**
         * created_at : 1489060085463
         * updated_at : 1489060963928
         * id : 1157938031951875
         * data : {"status":0,"live_name":"马泽宇直播","pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489070127924&di=1265b69c4092d0f9c4be4095eead6021&imgtype=0&src=http%3A%2F%2Fa.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fca1349540923dd54261fa262d309b3de9c82484f.jpg","live_type":0}
         * uid : 1157677280460801
         * user : {"user_data":{"user_name":"小源","avatar":"http://d.hiphotos.baidu.com/zhidao/wh%3D600%2C800/sign=44efbe491e30e924cff194377c38423e/dcc451da81cb39dbf51ac417d1160924aa18309c.jpg"},"id":1157677280460801,"created_at":1489044543163,"updated_at":1489044543225}
         */

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable{
            private long created_at;
            private long updated_at;
            private long id;
            /**
             * status : 0
             * live_name : 马泽宇直播
             * pic : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489070127924&di=1265b69c4092d0f9c4be4095eead6021&imgtype=0&src=http%3A%2F%2Fa.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fca1349540923dd54261fa262d309b3de9c82484f.jpg
             * live_type : 0
             */

            private DataBean data;
            private long uid;
            /**
             * user_data : {"user_name":"小源","avatar":"http://d.hiphotos.baidu.com/zhidao/wh%3D600%2C800/sign=44efbe491e30e924cff194377c38423e/dcc451da81cb39dbf51ac417d1160924aa18309c.jpg"}
             * id : 1157677280460801
             * created_at : 1489044543163
             * updated_at : 1489044543225
             */

            private UserBean user;

            public long getCreated_at() {
                return created_at;
            }

            public void setCreated_at(long created_at) {
                this.created_at = created_at;
            }

            public long getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(long updated_at) {
                this.updated_at = updated_at;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public DataBean getData() {
                return data;
            }

            public void setData(DataBean data) {
                this.data = data;
            }

            public long getUid() {
                return uid;
            }

            public void setUid(long uid) {
                this.uid = uid;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public static class DataBean implements Serializable{
                private int status;
                private String live_name;
                private String pic;
                private int live_type;

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getLive_name() {
                    return live_name;
                }

                public void setLive_name(String live_name) {
                    this.live_name = live_name;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public int getLive_type() {
                    return live_type;
                }

                public void setLive_type(int live_type) {
                    this.live_type = live_type;
                }
            }

            public static class UserBean implements Serializable {
                /**
                 * user_name : 小源
                 * avatar : http://d.hiphotos.baidu.com/zhidao/wh%3D600%2C800/sign=44efbe491e30e924cff194377c38423e/dcc451da81cb39dbf51ac417d1160924aa18309c.jpg
                 */

                private UserDataBean user_data;
                private long id;
                private long created_at;
                private long updated_at;

                public UserDataBean getUser_data() {
                    return user_data;
                }

                public void setUser_data(UserDataBean user_data) {
                    this.user_data = user_data;
                }

                public long getId() {
                    return id;
                }

                public void setId(long id) {
                    this.id = id;
                }

                public long getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(long created_at) {
                    this.created_at = created_at;
                }

                public long getUpdated_at() {
                    return updated_at;
                }

                public void setUpdated_at(long updated_at) {
                    this.updated_at = updated_at;
                }

                public static class UserDataBean implements Serializable{
                    private String user_name;
                    private String avatar;

                    public String getUser_name() {
                        return user_name;
                    }

                    public void setUser_name(String user_name) {
                        this.user_name = user_name;
                    }

                    public String getAvatar() {
                        return avatar;
                    }

                    public void setAvatar(String avatar) {
                        this.avatar = avatar;
                    }
                }
            }
        }
    }
}
