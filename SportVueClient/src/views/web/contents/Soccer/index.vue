<template>
  <v-row>
    <v-col class="d-flex" cols="12" sm="2">Left</v-col>
    <v-col class="d-flex" cols="12" sm="8"
      ><div>
        <b-tabs content-class="mt-3" pills card>
          <b-tab title="Tất cả giải đấu" active>
            <div v-for="(item1, index) in tournamet" :key="index">
              <p>
                <b>{{item1.nameTour}}</b
                ><button style="float: right">Bảng xếp hạng</button>
              </p>
              <b-row v-for="(item2,index) in item1.schedule" :key="index"
                v-b-popover.hover.right="'Kích vào để xem chi tiết'"
                @click="a"
                style="border: solid black 1px"
              >
                <b-col cols="12" sm="2"> {{item2.status==0?'Sắp diễn ra':item2.status==1?'Đang diễn ra':'Kết thúc'}} </b-col>
                <b-col cols="12" sm="10" class="text-center">
                  Team1 ?-? Team2
                </b-col>
              </b-row>
            </div>
          </b-tab>
          <b-tab title="Đang diễn ra"><p>I'm the second tab</p></b-tab>
          <b-tab title="Kết thúc"><p>I'm a disabled tab!</p></b-tab>
          <b-tab title="Sắp diễn ra"><p>I'm a disabled tab!</p></b-tab>
        </b-tabs>
      </div></v-col
    >
    <v-col class="d-flex" cols="12" sm="2">Right</v-col>
  </v-row>
</template>
<script>
export default {
  data() {
    return {
      tournamet: "",
    };
  },
  created() {
    this.$store.dispatch("tournament/getAll").then((response) => {
      this.tournamet = response.data;
    });
  },
  methods: {
    a() {
      alert("ok");
    },
  },
};
</script>