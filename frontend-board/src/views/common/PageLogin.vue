<template>
  <div>
    <div id="loginForm">
      <h2>로그인</h2>
      <div>
        <form @submit.prevent="fnLogin">
          <!-- submit.prevent -> form의 기본 동작 이벤트인 새로고침을 하지 않도록 제어 -->
          <p>
            <input name="uid" placeholder="Enter your ID" v-model="user_id"><br>
          </p>
          <p>
            <input name="password" placeholder="Enter your password" v-model="user_pw" type="password"><br>
          </p>
          <p>
            <button class="login__button" type="submit">Login</button>
          </p>
        </form> 
      </div>
    </div>
  </div>
</template>

<script>

import {mapActions, mapGetters} from 'vuex'

export default {
  data() {
    return {
      user_id: '',
      user_pw: '',
    }
  },
  methods: {

    ...mapActions(['login']),

    async fnLogin() {
      if (this.user_id === '') {
        alert('ID를 입력하세요.')
        return
      }
      if (this.user_pw === '') {
        alert('비밀번호를 입력하세요.')
        return
      }
      //로그인 API 호출 
      try {
        let loginResult = await this.login({user_id: this.user_id, user_pw: this.user_pw})
        if (loginResult) {
          this.$router.push ({
            path: './boardList'
        })
        }
      } catch (err) {
        if (err.message.indexOf('Network Error') > -1) {
          alert('서버에 접속할 수 없습니다. 상태를 확인해주세요.')
        } else {
          alert('로그인 정보를 확인할 수 없습니다.')
        }
      }
    }
  },
  computed: {
    ...mapGetters({
      errorState: 'getErrorState'
    })
  }
}
</script>