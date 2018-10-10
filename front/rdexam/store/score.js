/**
 * 得分数据
 * @author bili
 * @date 18-10-10
 */
export const state = () => ({
  /**
   * 当月答题和累计答题
   */
  allSubmit: 0,//累计答题
  allRight: 0,//累计正确
  allError: 0,//累计错误
  mouthSub: 0,//当月答题
  mouthRight: 0,//当月正确
  mouthError: 0//当月错误
});
export const mutations = {
  updateScore(state, newScore) {
    state = newScore;
  },
  addSub(state) {
    state.mouthSub++;
    state.allSubmit++;
  },
  addRight(state) {
    state.allRight++;
    state.mouthRight++;
  },
  addError(state) {
    state.allError += 1;
    state.mouthError += 1;
  }
};
