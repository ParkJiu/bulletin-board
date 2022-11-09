<template>
  <div class="board">
    <div class="board__btn">
      <button class="board__button" v-on:click="fnUpdate">수정</button>
      <button class="board__button" v-on:click="fnDelete">삭제</button>
      <button class="board__button" v-on:click="fnList">목록</button>
    </div>
    <div class="board__contents">
      <h2>{{title}}</h2>
      <span>{{author}}</span>
      <div class="divider__col"></div>
      <span>{{created_at}}</span>
      <br>
      <br>
      <div class="divider__row"></div>
      
      <!-- <div> -->
        
        <br>
      <!-- </div> -->
    </div>
    <div class="board__contents">
      <span>{{contents}}</span>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      requestBody: this.$route.query,
      idx: this.$route.query.idx,

      title: '',
      author: '',
      contents: '',
      created_at: ''
    }
  },
  mounted() {
    this.fnGetView()
  },
  methods: {
    fnGetView() {
      this.$axios.get(this.$serverUrl + '/board/' + this.idx, {
        params: this.requestBody
      }).then((res) => {
        this.title = res.data.title
        this.author = res.data.author
        this.contents = res.data.contents
        this.created_at = res.data.created_at
      }).catch((err) => {
        if (err.message.indexOf('Network Error') > -1) {
          alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
        }
      })
    },
    fnList() {
      delete this.requestBody.idx
      this.$router.push({
        path: './boardList',
        query: this.requestBody
      })
    },
    fnUpdate() {

      if (this.title === '') {
          alert("제목을 입력해주세요.")
          return;
        } else if (this.contents === ''){
          alert("내용을 입력해주세요.")
          return;
        }

      this.$router.push({
        path: './boardInsert',
        query: this.requestBody
      })
    },
    fnDelete() {
      if (!confirm("삭제하시겠습니까?")) 
      return;

      this.$axios.delete(this.$serverUrl + '/board/' + this.idx, {})
          .then(() => {
            alert('삭제되었습니다.')
            this.fnList();
          }).catch((err) => {
        console.log(err);
      })
    }
  }
}
</script>