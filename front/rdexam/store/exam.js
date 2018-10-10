/**
 * 所有的试题管理数据模型类
 */
export const state = () => ({
  pageUrl: '/exam/list',
  pageSize: 20,
  currentPage: 1,
  activeRow: {},
  examDatas: []
});

export const mutations = {
  getPageData(state,pageData){
    state.examDatas = pageData
  }
};

 * 分数sotre
 * @author bili
 * @date 18-9-30
 */
export const state = ()=>({
  /**
   * 当月答题和累计答题
   */
  score:{
      allSubmit:0,//累计答题
      allRight:0,//累计正确
      allError:0,//累计错误
      mouthSub:0,//当月答题
      mouthRight:0,//当月正确
      mouthError:0//当月错误
  },
  /**
   * 当前题目
   */
  nowExam:{
    id:"",
    title:"",//当前答题题目
    options:[]//选项
  }
});
export const mutations = {
  updateExam(state,newExam){
    state.nowExam = newExam;
  },
  updateScore(state,newScore){
    state.score = newScore;
  },
  addSub(state){
    state.score.mouthSub++;
    state.score.allSubmit++;
  },
  addRight(state){
    state.score.allRight++;
    state.score.mouthRight++;
  },
  addError(state){
    state.score.allError+=1;
    state.score.mouthError+=1;
  }
}
