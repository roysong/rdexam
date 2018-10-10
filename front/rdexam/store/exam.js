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

