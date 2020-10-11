<template>
  <v-container class="pt-2">
    <v-row>
      <v-col cols="12" sm="2" style="padding: 5px" class="pr-2">
        <v-card >
          <v-list style="background-color: white">
            <v-subheader><b style="color: red">Các giải đấu</b></v-subheader>
            <v-list-item v-for="item in items" :key="item.title">
              <v-list-item-content>
                <a :href="$router.resolve({path: '/DetailTournametSoccer/' + item.idTour}).href" style="color:black">               
                  <v-list-item-title v-text="item.nameTour"></v-list-item-title
                ></a>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-card>
      </v-col>
      <v-col cols="12" sm="8" style="padding: 0px" class="pt-1"
        ><router-view></router-view
      ></v-col>
      <v-col cols="12" sm="2" style="padding: 5px">
        <v-card>
          <v-list style="background-color: white">
            <v-subheader><b style="color: green">BẢNG XẾP HẠNG</b></v-subheader>
            <v-list-item v-for="(item, index) in rank" :key="index">
              <v-list-item-content>
                {{ index + 1 }}.{{ item.name }}
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
export default {
  data() {
    return {
      items: [],
      rank: [],
    };
  },
  created() {
    this.getTournament();
    this.getRank();
  },
  methods: {
    getTournament() {
      this.$store
        .dispatch("tournament/getByType", "TableTennis")
        .then((response) => {
          this.items = response.data;
        });
    },
    getRank() {
      this.$store
        .dispatch("tournament/getRank", "TableTennis")
        .then((response) => {
          this.rank = response.data;
        });
    },
  },
};
</script>