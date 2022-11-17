//mutation -> 서버에 데이터를 업데이트 하도록 서버에 네트워크 호출을 실시
//vuex에서는 필요하나, pinia로 넘어가면서 mutation은 제거됨

import * as types from './mutation_types'

export default {
  [types.USER_ID] (state, userId) {
    state.userId = userId
  },
  [types.ERROR_STATE] (state, errorState) {
    state.errorState = errorState
  },
  [types.IS_AUTH] (state, isAuth) {
    state.isAuth = isAuth
  }
}