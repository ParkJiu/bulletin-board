<template>
  <div class="board">
    <div class="board__btn">
      <button class="board__button" @click="fnWrite()">글쓰기</button>
    </div>
    <div class="board__list">
      <table>
        <thead>
          <tr>
          <th>No</th>
          <th>제목</th>
          <th>작성자</th>
          <th>등록일시</th>
          </tr>
        </thead>
        <tbody>
        <tr v-for="(row, idx) in list" :key="idx">
          <td width="80">{{ row.idx }}</td>
          <td id="board__list__a"><a v-on:click="fnView(`${row.idx}`)">{{ row.title }}</a></td>
          <td width="150">{{ row.author }}</td>
          <td width="150">{{ row.created_at.slice(0,10)}}</td>

        </tr>
      </tbody>
    </table>
    <div v-if="paging.total_list_cnt > 0">
      <span class="pagination">
      <a class="pagination__active arrow" href="javascript:;" @click="fnPage(1)">&lt;&lt;</a>
      <a class="pagination__active arrow" href="javascript:;" v-if="paging.start_page > 10" @click="fnPage(`${paging.start_page-1}`)"
        >&lt;</a>
      <template v-for=" (n,index) in paginavigation()">
          <template v-if="paging.page==n">
              <strong class="pagination__active pagination__active__clicked" :key="index">{{ n }}</strong>
          </template>
          <template v-else>
              <a class="pagination__active" href="javascript:;" @click="fnPage(`${n}`)" :key="index">{{ n }}</a>
          </template>
      </template>
      <a class="pagination__active arrow" href="javascript:;" v-if="paging.total_page_cnt > paging.end_page"
        @click="fnPage(`${paging.end_page+1}`)">&gt;</a>
      <a class="pagination__active arrow" href="javascript:;" @click="fnPage(`${paging.total_page_cnt}`)">&gt;&gt;</a>
      </span>
    </div>
    </div>
  </div>
</template>
<script>

export default {

  //mounted(): 생성된 이후 화면이 표시될 때 발생
  mounted() {
    this.fnGetList()
  },
  name: "BoardList",
  data() { //변수생성
    return {
      requestBody: {}, //리스트 페이지 데이터전송
      list: {}, //리스트 데이터
      paging: {
        block: 0, //현재 블럭
        end_page: 0, //블럭 마지막 페이지
        next_block: 0, //다음 블럭 시작 페이지
        page: 0, //현재 페이지
        page_size: 0, //페이지 당 보여지는 게시글 최대 개수
        prev_block: 0, //이전 블럭 마지막 페이지
        start_index: 0, //인덱스
        start_page: 0, //블럭 시작 페이지
        total_block_cnt: 0, //총 블럭 수 totalBlockCnt = (int) Math.ceil(totalPageCnt * 1.0 / blockSize);
        total_list_cnt: 0, //총 게시글 수
        total_page_cnt: 0, //총 페이지 수 totalPageCnt = (int) Math.ceil(totalListCnt * 1.0 / this.pageSize);
      }, //페이징 데이터
      page: this.$route.query.page ? this.$route.query.page : 1,
      size: this.$route.query.size ? this.$route.query.size : 10,
      paginavigation: () => 
      { //페이징 처리 for문 커스텀
        let pageNumber = [] //;
        let start_page = this.paging.start_page;
        let end_page = this.paging.end_page;
        for (let i = start_page; i <= end_page; i++) 
        pageNumber.push(i);
        return pageNumber;
      }
    }

  },

  methods: {
    fnGetList() {
      console.log(this.paging);
      this.requestBody = { // 데이터 전송        
        page: this.page,
        size: this.size
      }

      this.$axios.get(this.$serverUrl + "/board/list", {
        params: this.requestBody,
        headers: {}
      }).then((res) => {      
        if (res.data.result_code === "OK") {
          this.list = res.data.data
          this.paging = res.data.pagination
        }

      }).catch((err) => {
        if (err.message.indexOf('Network Error') > -1) {
          alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
        }
      })
    },
    fnView(idx) {
      this.requestBody.idx = idx;
      this.$router.push ({
        path: './boardDetail',
        query: this.requestBody
        })
      },
      fnWrite() {
        this.$router.push({
          path: 'boardInsert'
        })
      },
      fnPage(n) {
        if(this.page !== n ){
          this.page = n
          this.fnGetList();
        }
      },
  },
}
</script>
<style scoped>
th, td { 
  border: 1px solid black; padding: 10px;
  }

</style>