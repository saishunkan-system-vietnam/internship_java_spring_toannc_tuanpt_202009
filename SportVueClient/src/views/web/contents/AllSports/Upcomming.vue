<template>
  <v-expansion-panels multiple>
    <v-expansion-panel v-for="(item, i) in upComingMatch" :key="i">
      <v-expansion-panel-header disable-icon-rotate
        ><template v-if="item.type === 'Football'"
          ><v-icon style="max-width: 30px" medium color="green darken-2">
            mdi-soccer
          </v-icon></template
        >
        <template v-else-if="item.type === 'BaskestBall'"
          ><v-icon style="max-width: 30px" medium color="orange darken-2">
            sports_basketball
          </v-icon></template
        >
        <template v-else-if="item.type === 'TableTennis'"
          ><v-icon style="max-width: 30px" medium color="blue darken-2">
            sports_tennis
          </v-icon></template
        >
        <h4>{{ item.type }}</h4>
      </v-expansion-panel-header>

      <v-expansion-panel-content>
        <v-expansion-panels inset>
          <v-expansion-panel
            v-for="(tournament, t) in item.tournament"
            :key="t"
          >
            <v-expansion-panel-header disable-icon-rotate>
              <p style="font-weight: bold">{{ tournament.nameTour }}</p>
            </v-expansion-panel-header>
            <v-expansion-panel-content
              v-for="(item, s) in tournament.schedule"
              :key="s"
            >
              <v-list dense>
                <v-list-item-group color="primary">
                  <v-list-item>
                    <v-list-item-content>
                      <v-row
                        v-b-popover.hover.right="'Kích vào để xem chi tiết'"
                        @click="detail(item)"
                        v-model="open"
                      >
                        <v-col cols="12" sm="4">Up Coming</v-col>
                        <v-col cols="12" sm="8">
                          <v-row>
                            <v-col> {{ item.team[0].nameTeam }}</v-col>
                            <v-col
                              >{{ item.status == 2 ? item.scoreTeam1 : "?" }}-
                              {{
                                item.status == 2 ? item.scoreTeam2 : "?"
                              }}</v-col
                            >
                            <v-col>{{ item.team[1].nameTeam }}</v-col>
                          </v-row></v-col
                        >
                      </v-row>
                    </v-list-item-content>
                  </v-list-item>
                </v-list-item-group>
              </v-list>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-expansion-panel-content>
    </v-expansion-panel>
  </v-expansion-panels>
</template>
<script>
import Schedule from "../../../../models/schedule";
export default {
  data() {
    return {
      disabled: false,
      open: false,
      upComingMatch: [],
    };
  },
  mounted() {
    // console.log("Run upcoming");
    this.recivceData();
    // console.log(this.upComingMatch);
  },
  methods: {
    recivceData() {
      let self = this;
      this.$store.dispatch("tournament/getToursByType").then((res) => {
        self.upComingMatch = res.data.map((v) => {
          let newTournament = v.tournament
            .filter((i) => {
              return i.status != 2;
            })
            .map((k) => {
              let newSchedule = k.schedule.filter((j) => {
                return j.status == 0;
              });
              k.schedule = newSchedule;
              return k;
            });
          v["tournament"] = newTournament;
          return v;
        });
      });
    },

    detail(item) {
      this.detailScore = item;
      this.open = true;
      var myWindow = window.open(
        "http://localhost:8080/detail/" + item.idSchedule,
        "myWindow",
        "width=600px,height=600"
      );
    },
  },
};
</script>
<style>
</style>