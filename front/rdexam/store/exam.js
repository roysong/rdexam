/**
 * 所有的试题管理数据模型类
 */
export const state = () => ({
  pageUrl: '/exam/list',
  pageSize: 20,
  currentPage: 1,
  activeRow: {},
  examDatas: [],
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
  getPageData(state,pageData){
    let activeData = state.examDatas.filter(i=>i.tab===pageData.tab);
    if(activeData.length>0){
      let index = state.examDatas.indexOf(activeData[0]);
      state.examDatas.splice(index,1,pageData);
    }else{
      state.examDatas.push(pageData);
    }
  },
  updateExam(state,newExam){
    state.nowExam = newExam;
  }
};
