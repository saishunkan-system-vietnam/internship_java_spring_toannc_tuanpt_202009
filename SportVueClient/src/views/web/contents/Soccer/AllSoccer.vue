<template>
  <div>
    <v-list dense>
      <div v-for="(items, index) in data" :key="index">
        <v-subheader
          ><h4 style="color: red; background: beige">
            {{ items.nameTour }}
          </h4></v-subheader
        >
        <v-list-item-group
          v-for="(item, index) in items.schedule"
          color="primary"
          :key="index"
        >
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title>
                <v-row
                  v-b-popover.hover.right="'Kích vào để xem chi tiết'"
                  @click="detail(item)"
                  v-model="open"
                >
                  <v-col cols="12" sm="4">{{
                    item.status == 0
                      ? "Sắp diễn ra"
                      : item.status == 1
                      ? "Đang diễn ra"
                      : "Kết thúc"
                  }}</v-col>
                  <v-col cols="12" sm="6">
                    <v-row>
                      <v-col> {{ item.team[0].nameTeam }}</v-col>
                      <v-col
                        >{{ item.status == 2 ? item.scoreTeam1 : "?" }}-
                        {{ item.status == 2 ? item.scoreTeam2 : "?" }}</v-col
                      >
                      <v-col>{{ item.team[1].nameTeam }}</v-col>
                    </v-row></v-col
                  >
                </v-row>
              </v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </div>
    </v-list>
  </div>
</template>
<script>
export default {
  components: {},
  data() {
    return {
      data: "",
      open: false,
      detailScore: "",
    };
  },
  created() {
    this.$store
      .dispatch("tournament/getByType", "Football")
      .then((response) => {
        this.data = response.data;
        console.log(this.data);
      });
  },
  methods: {
    detail(data) {
      this.detailScore = data;
      this.open = true;
      var myWindow = window.open(
        "http://localhost:8080/soccer/detail/" + data.idSchedule,
        "myWindow",
        "width=600px,height=600"
      );
    },
  },
};
</script>
 