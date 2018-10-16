export const state = () => ({
  menuCollpse: false,
  activeTabName: '', //当前tab页名称
  tabDatas: [] //已打开的tab页
});

export const mutations = {
  toggle(state) {
    state.menuCollpse = !state.menuCollpse
  },
  activeTab(state, tabName) {
    state.activeTabName = tabName
  },
  removeTab(state, targetName) {
    let tabs = state.tabDatas;
    let activeName = state.activeTabName;
    if (activeName === targetName) {
      tabs.forEach((tab, index) => {
        if (tab.name === targetName) {
          let nextTab = tabs[index + 1] || tabs[index - 1];
          if (nextTab) {
            activeName = nextTab.name;
          }
        }
      });
    }
    state.activeTabName = activeName;
    state.tabDatas = tabs.filter(tab => tab.name !== targetName);
  },
  addTab(state, itemName) {
    if (!itemName) return;
    state.activeTabName = itemName;
    let tabs = state.tabDatas;
    let alreadyOpenTab = tabs.filter(t => t.name === itemName);
    if (alreadyOpenTab && alreadyOpenTab.length > 0) return;//已打开的页面不重新加载组件
    let tabComponent;
    switch (itemName) {
      case 'webtec':
      case 'javatec':
      case 'testtec':
      case 'transtec':
      case 'opertec':
      case 'setec':
        tabComponent = resolve => require([`~/components/admin/${'ExamController'}`], resolve);
        break;
      default:
        return;
    }
    let item = {
      title: 'Tab ' + itemName,
      name: itemName,
      content: tabComponent
    };
    state.tabDatas.push(item);
  }
};

